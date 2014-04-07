package com.example.meliproject.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dguzik on 4/7/14.
 */
public class SimpleListAdapter extends BaseAdapter {
    private Activity activity;
    private static LayoutInflater inflater=null;

    public SimpleListAdapter(Activity a) {
        activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return 3;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

            TextView title = (TextView)vi.findViewById(R.id.title); // title
            TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
            TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
            ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

            // Setting all values in listview
            title.setText("Item " + position);
            artist.setText("text1");
            duration.setText("$ 123.00");
        int res = position == 0 ? R.drawable.img1 : (position == 2 ? R.drawable.img2 : R.drawable.img3);
            thumb_image.setImageResource(res);


    return vi;


    }
}
