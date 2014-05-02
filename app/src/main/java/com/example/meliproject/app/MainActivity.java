package com.example.meliproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.meliproject.app.client.SearchClient;


public class MainActivity extends ActionBarActivity {
    public static final String TEXT_SEARCH = "com.example.myapplication3.app.TEXT_SEARCH" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if (savedInstanceState != null)
            this.onRestoreInstanceState(savedInstanceState);*/
        final MainActivity m = this;
        Log.w("Main", "Creating main activity with " + (savedInstanceState != null ? savedInstanceState.toString() : "null"));
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(m, ListActivity.class);
                EditText editText = (EditText) findViewById(R.id.editText);
                String message = editText.getText().toString();
                intent.putExtra(TEXT_SEARCH, message);

                startActivity(intent);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.w("Main", "Saving instance state");
        super.onSaveInstanceState(savedInstanceState);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        savedInstanceState.putString("searchString", message);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.w("Main", "Storing instance state");
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        String message = savedInstanceState.getString("searchString");
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveData() {

    }
    private void restoreData() {

    }

}
