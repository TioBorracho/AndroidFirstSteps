package com.example.meliproject.app.client;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.meliproject.app.CallbackListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dguzik on 4/24/14.
 */
public class SearchClient {
    private CallbackListener callback;
    private final int LIMIT = 15;

    private int actualPage = 0;
    private String searchString;

    List<String> cache = new ArrayList<String>();

    private class NetworkTask extends AsyncTask<String, Void, String> {
        private SearchClient listener;

        public NetworkTask(SearchClient listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... params) {
            String link = params[0];
            HttpGet request = new HttpGet(link);
            AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
            try {
                Log.w("HTTP", "downloading " + link);
                HttpResponse r = client.execute(request);
                String s = EntityUtils.toString(r.getEntity());
                return s;
            } catch (IOException e) {

                return null;
            } catch (Exception e) {
                Log.w("HTTP", "Error getting" + e.getMessage());
                return null;
            } finally {
                client.close();
            }
        }
        @Override
        protected void onPostExecute(String result) {
            //Do something with result
            try {
                listener.cacheAndReturn(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchItems(String sString, CallbackListener context) throws UnsupportedEncodingException {
        this.callback = context;
        actualPage = 0;
        searchString = URLEncoder.encode(sString, "UTF-8");
        String link = "https://api.mercadolibre.com/sites/MLA/search?q=" + searchString + "&limit=" + LIMIT + "&offset=" +
                (LIMIT*actualPage);
        new NetworkTask(this).execute(link);

    }
    public void nextResults() throws JSONException {
        actualPage++;
        if (cache.size() > actualPage) {
            String cachedResult = cache.get(actualPage);
            callback.callBack(new JSONObject(cachedResult));
        } else {
            String link = "https://api.mercadolibre.com/sites/MLA/search?q=" + searchString + "&limit=" + LIMIT + "&offset=" +
                    (LIMIT*actualPage);
            new NetworkTask(this).execute(link);
        }

    }
    public void cacheAndReturn(String result ) throws JSONException {
        cache.add(actualPage, result);
        callback.callBack(new JSONObject(result));
    }


}
