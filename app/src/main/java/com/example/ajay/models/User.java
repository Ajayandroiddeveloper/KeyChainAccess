package com.example.ajay.models;

/**
 * Created by Ajay on 5/21/2017.
 */

public class User {

    //instance variables
    private String name;
    private String eMail;
    private String phoneNumber;
    private String password;
    private static User user;


    //Constructor
    private User(String name,String eMail,String phoneNumber,String password){
        this.name = name;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.password = password;

    }

    //Setters and getters
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setEmail(String eMail){
        this.eMail = eMail;
    }
    public String getEmail(){
        return this.eMail;
    }

    public void setPhoneNumber(String phone){
        this.phoneNumber = phone;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public static User getUserInstance(){
        if(user == null){
            user = new User(null,null,null,null);
        }
        return user;
    }

}
