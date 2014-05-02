package com.example.meliproject.app.Entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dguzik on 4/23/14.
 */
public class Item {
    String id;
    String title;
    String subtitle;
    Double price;
    Integer quantity;

    public  Item() {}

    public  Item(JSONObject json) throws JSONException {
        id = json.getString("id");
        title = json.getString("title");
        subtitle = json.getString("subtitle");
        price = json.getDouble("price");
        quantity = json.getInt("available_quantity");
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
