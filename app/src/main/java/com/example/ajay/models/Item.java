package com.example.ajay.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajay on 5/23/2017.
 */

public class Item extends Category implements Parcelable{

    //Instance variables
    private String password;

    //Constructor
    public Item(int imageValue, String name, String description, long createdDateTime, String password) {
        super(imageValue,name, description, createdDateTime,null);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Item(){
        super();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getName());
        dest.writeString(this.getDescription());
        dest.writeString(this.password);
        dest.writeLong(this.getCreatedDateTime());
    }

    private Item(Parcel in){
        this.name = in.readString();
        this.description = in.readString();
        this.password = in.readString();
        this.createdDateTime = in.readLong();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item [] newArray(int size) {
            return new Item[size];
        }
    };
}
