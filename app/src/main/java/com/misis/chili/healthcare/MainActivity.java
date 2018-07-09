package com.misis.chili.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListAdapter adapter;
    public ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list_cards);
        adapter = new ListAdapter(this, getData());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(MainActivity.this, Details.class);
                appInfo.putExtra("person", (Card) adapter.getItemAtPosition(position));
                startActivity(appInfo);
            }
        });
    }

    public void onListItemClick(ListView Parent, View v, int position,long id){
        startActivity(new Intent(MainActivity.this, Details.class));
        
    }


    private ArrayList getData()
    {
        ArrayList<Card> cards=new ArrayList<>();
        Card s;

        s=new Card();
        s.setName("Aunt May");
        s.setStatus("Bad");
        s.setImage(R.drawable.grandma_1);
        s.setBpm("147");
        s.setTemp("");
        s.setSteps("7254");
        s.setInhaler(13);
        cards.add(s);

        s=new Card();
        s.setName("Uncle Ben");
        s.setStatus("OK");
        s.setImage(R.drawable.grandpa_1);
        s.setBpm("67");
        s.setTemp("");
        s.setSteps("4236");
        s.setInhaler(2);
        cards.add(s);

        s=new Card();
        s.setName("Piter Parker");
        s.setStatus("No web");
        s.setImage(R.drawable.piter);
        s.setBpm("90");
        s.setTemp("");
        s.setSteps("11985");
        s.setInhaler(4);
        cards.add(s);

        return cards;
    }
}
