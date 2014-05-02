package com.example.meliproject.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.meliproject.app.Entities.Item;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by dguzik on 4/7/14.
 */
public class ItemListAdapter extends BaseAdapter {
    private Activity activity;
    private static LayoutInflater inflater=null;
    public List<Item> items;
    private final DecimalFormat formatter = new DecimalFormat("#.##");



    public ItemListAdapter(Activity a, List items) {
        activity = a;
        this.items = items;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return items.size();
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
        //TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView price = (TextView)vi.findViewById(R.id.price); // duration
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        Item it = items.get(position);
        // Setting all values in listview
        title.setText(it.getTitle());
        Double priceVal = it.getPrice();
        price.setText(priceVal == null ? "" : formatter.format(priceVal));


        return vi;


    }
}
