package com.misis.chili.healthcare;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Chili on 16.11.2017.
 */

class Downloader extends AsyncTask<String, Void, String> {

    Details mActivity;

    public Downloader(Details activity) {
        mActivity = activity;
    }

    private Exception exception;

    protected String doInBackground(String... url) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url[0]);
        try {
            HttpResponse response = httpclient.execute(httpget);
            if (response.getStatusLine().getStatusCode() == 200) {
                String server_response = EntityUtils.toString(response.getEntity());
                return server_response;
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            return "Exception";
        }
    }

    protected void onPostExecute(String response) {
        
        mActivity.setData(response);
    }
}
