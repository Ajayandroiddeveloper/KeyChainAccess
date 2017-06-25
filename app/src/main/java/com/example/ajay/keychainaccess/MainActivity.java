package com.example.ajay.keychainaccess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ajay.models.User;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mRegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginButton = (Button)findViewById(R.id.login_btn);
        mRegisterButton = (Button)findViewById(R.id.register_btn);

        loginButtonClicked();
        registerButtonClicked();


    }

    public void loginButtonClicked(){
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(v.getContext(),LoginActivity.class);
                startActivity(loginIntent);
                //startActivityForResult(loginIntent,1);
            }
        });
    }

    public void registerButtonClicked(){
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(v.getContext(),RegisterActivity.class);
                startActivity(registerIntent);
                //startActivityForResult(registerIntent,2);
            }
        });
    }


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            Log.i("Result","Result came back from loginActivity");
            String value = data.getStringExtra("key1");
            Log.i("login data",value);
        }else if (resultCode == 2){
            Log.i("Result","Result came back from registerActivity");
        }

    }*/
}
