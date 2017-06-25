package com.example.ajay.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajay on 5/23/2017.
 */

public class Category implements Serializable {

    //Instance variables
    private int imageValue;
    protected String name;
    protected String description;
    protected long createdDateTime;
    private ArrayList<Item> items;

    //Constructor
    public Category(int imageValue,String name,String description,long createdDateTime,ArrayList<Item> items){
        this.imageValue = imageValue;
        this.name = name;
        this.description = description;
        this.createdDateTime = createdDateTime;
        this.items = items;
    }
    public Category(){

    }

    //setters and getters
    public void setImageValue(int image) { this.imageValue = image; }

    public int getImageValue() {
        return this.imageValue;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCreatedDateTime(long createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public long getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }
}
