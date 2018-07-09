package com.misis.chili.healthcare;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Details extends AppCompatActivity{
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        Card s = (Card)i.getSerializableExtra("person");
        ImageView img= (ImageView) this.findViewById(R.id.person_photo);
        TextView nameTxt= (TextView) this.findViewById(R.id.person_name);
        TextView statTxt= (TextView) this.findViewById(R.id.person_status);
        TextView tempTxt = (TextView) this.findViewById(R.id.textTemp);
        TextView bpmTxt = (TextView) this.findViewById(R.id.textHeart);
        TextView stepsTxt = (TextView) this.findViewById(R.id.textSteps);
        nameTxt.setText(s.getName());
        statTxt.setText(s.getStatus());
        bpmTxt.setText("1 minute average: " + s.getBpm());
        stepsTxt.setText("Today: " + s.getSteps() + "steps");
        img.setImageResource(s.getImage());
        TextView usedTxt = (TextView) this.findViewById(R.id.person_used);
        usedTxt.setText("Inhaler used: " + s.getInhaler());
        if (s.getInhaler() > 12)
        {
            View someView = findViewById(R.id.lay);
            someView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            statTxt.setText("Not inhaled");
        }
        //getInfo();
        callAsynchronousTask();
    }

    public void checkInhaler()
    {

    }

    public void getInfo()
    {
        new Downloader(this).execute("https://chili-devworks-course.eu-gb.mybluemix.net/temp");
    }

    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            getInfo();
                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1000); 
    }

    public void setData(String res)
    {
        try {
            JSONObject mainObject = new JSONObject(res);
            TextView tempTxt = (TextView) this.findViewById(R.id.textTemp);
            String temp = mainObject.getString("lastTemperature");
            tempTxt.setText("Last measurement: " + mainObject.getString("lastTemperature"));
            double t = Double.parseDouble(temp.replace(',','.'));
            if (t > 27)
            {
                TextView statTxt= (TextView) this.findViewById(R.id.person_status);
                statTxt.setText("High temp!");
                View someView = findViewById(R.id.lay);
                someView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }
        catch (Exception e)
        {}
    }

    public void call(View v){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+"+79652829946"));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    10);
            return;
        }else {    
            try{
                startActivity(callIntent); 
            }
            catch (android.content.ActivityNotFoundException ex){
                
            }
        }
        startActivity(callIntent);
    }
}
