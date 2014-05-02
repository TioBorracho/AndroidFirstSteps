package com.example.meliproject.app.utils;

import android.util.Log;
import android.widget.AbsListView;

import com.example.meliproject.app.client.SearchClient;

import org.json.JSONException;

/**
 * Created by dguzik on 5/2/14.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private SearchClient s;

    public EndlessScrollListener() {
    }
    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }
    public EndlessScrollListener(SearchClient c) {
        s = c;
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
            Log.w("SCroll", "more preciouss");
            try {
                s.nextResults();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //new LoadGigsTask().execute(currentPage + 1);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}