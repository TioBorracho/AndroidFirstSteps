package com.example.meliproject.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meliproject.app.Entities.Item;
import com.example.meliproject.app.client.SearchClient;
import com.example.meliproject.app.utils.EndlessScrollListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListActivity extends ActionBarActivity implements CallbackListener {

    private SearchClient searchClient = new SearchClient();
    List<Item> items = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String searchText = intent.getStringExtra(MainActivity.TEXT_SEARCH);
        String message = getString(R.string.buscaste_text) + intent.getStringExtra(MainActivity.TEXT_SEARCH);


        TextView tView = (TextView) findViewById(R.id.textView);
        tView.setText(message);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnScrollListener(new EndlessScrollListener(searchClient));

        try {
            searchClient.searchItems(searchText, this);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void callBack(Object obj) throws JSONException {
        JSONObject json = (JSONObject) obj;
        //fill the adapter
        ListView lv = (ListView) findViewById(R.id.listView);

        try {
            for (int i = 0; i < json.getJSONArray("results").length(); ++i) {
                JSONObject jitem = json.getJSONArray("results").getJSONObject(i);
                Log.w("JSON", jitem.toString());
                items.add(new Item(jitem));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ItemListAdapter a;
        if (lv.getAdapter()== null) {
            a = new ItemListAdapter(this, items);
            lv.setAdapter(a);
        } else {
            a = (ItemListAdapter) lv.getAdapter();
            a.notifyDataSetChanged();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.w("CLICK", Integer.valueOf(id).toString());

        if (id == R.id.action_settings) {
            return true;
        }
        Log.w("CLICK", Integer.valueOf(id).toString());
        if (id == R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class StableArrayAdapter extends ArrayAdapter<String> {

        final int INVALID_ID = -1;

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            if (position < 0 || position >= mIdMap.size()) {
                return INVALID_ID;
            }
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
