package com.example.ajay.keychainaccess;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ajay.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText mUserEmail;
    private EditText mUserPassword;
    private Button mUserLoginButton;
    private TextView mForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserEmail = (EditText) findViewById(R.id.userEmail_phone_et);
        mUserPassword = (EditText) findViewById(R.id.userPassword_et);
        mUserLoginButton = (Button) findViewById(R.id.userLogin_btn);
        mForgotPassword = (TextView) findViewById(R.id.forgetPassword_tv);

        loginButtonPresses();
        forgotPasswordPressed();

    }

    public void loginButtonPresses() {
        mUserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String emailOrPhone = mUserEmail.getText().toString();
                String password = mUserPassword.getText().toString();
                if (!emailOrPhone.isEmpty() && !password.isEmpty()) {

                    if (emailOrPhone.equals(User.getUserInstance().getEmail()) || emailOrPhone.equals(User.getUserInstance().getPhoneNumber()) && password.equals(User.getUserInstance().getPassword())) {
                        displayAlertDialog("Login is successful", "Success", 1);

                    } else {
                        displayAlertDialog("Email or password is Invalied", "Invalied", 0);
                    }


                } else {

                    displayAlertDialog("Email and password field is not empty", "Invalied", 0);
                }

                //For temporary purpose. when we uncomment above code below code should be removed
                Intent loginIntent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(loginIntent);


                /*Intent intent = new Intent();
                intent.putExtra("key1","Ajay");
                setResult(1,intent);
                finish();*/
            }
        });
    }

    public void forgotPasswordPressed() {
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_menu:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }

        return true;
    }

    public void displayAlertDialog(String message, String title, final int tagg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (tagg == 0) {

                } else {
                    Intent loginIntent = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
