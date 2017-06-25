package com.example.ajay.keychainaccess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread newThread = new Thread(){
           @Override
            public void run(){

               try {
                   sleep(4000);
                   Intent mainActivity = new Intent(SplashScreenActivity.this,MainActivity.class);
                   startActivity(mainActivity);
                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        };
        newThread.start();
    }
}
