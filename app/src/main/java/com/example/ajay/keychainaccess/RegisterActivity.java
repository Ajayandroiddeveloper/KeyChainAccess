package com.example.ajay.keychainaccess;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ajay.models.User;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mRegisterButton;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = (EditText) findViewById(R.id.name_et);
        mEmail = (EditText) findViewById(R.id.email_et);
        mPhone = (EditText) findViewById(R.id.phone_et);
        mPassword = (EditText) findViewById(R.id.password_et);
        mConfirmPassword = (EditText) findViewById(R.id.confirmPassword_et);
        mRegisterButton = (Button) findViewById(R.id.submit_btn);


        registerButtonPressed();
    }

    public void registerButtonPressed() {
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String eMail = mEmail.getText().toString();
                String phone = mPhone.getText().toString();
                String password = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                if (name.isEmpty()){
                    //We should show alert
                    displayAlertDialog("Name should not be empty","Name",0);
                    return;
                }else if(eMail.isEmpty()){
                    //We should show alert
                    displayAlertDialog("Email should not be empty","Email",0);
                    return;
                }else if(phone.isEmpty()){
                    //We should show alert
                    displayAlertDialog("Phone Number should not be empty","Phone",0);
                    return;
                }else if(password.isEmpty()){
                    //We should show alert
                    displayAlertDialog("Password should not be empty","Password",0);
                     return;
                }else if(confirmPassword.isEmpty()){
                    //We should show alert
                    displayAlertDialog("confirm Password should not be empty","Confirm Password",0);
                    return;
                }else{
                    Log.i("1","Main logic Starts here!");
                    /*if (!eMail.contains("@") && !eMail.contains(".")){
                        displayAlertDialog("Email is not valide","Email");
                        return;
                    }*/
                    if(!RegisterActivity.validate(eMail)){
                        displayAlertDialog("Email is not valide","Email",0);
                        return;
                    }
                    if(phone.length() < 5){
                        displayAlertDialog("Phone Number should contain atleast 5 characters","Phone Number",0);
                        return;
                    }
                    if(password.length() > 7){
                        if (!password.equals(confirmPassword)){
                            displayAlertDialog("Password should match with Confirm Password","Password mismatch",0);
                            return;
                        }
                    }else{
                        displayAlertDialog("Password should contain atleast 8 character","Very Weak Password!",0);
                        return;
                    }

                    Log.i("Main Logic","Its starts here now!");



                    User myUser = User.getUserInstance();
                    myUser.setEmail(eMail);
                    myUser.setName(name);
                    myUser.setPassword(password);
                    myUser.setPhoneNumber(phone);

                    SharedPreferences sharedPref = getSharedPreferences(String.valueOf(R.string.KeyChainAccessSharedPreference), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    Gson gson = new Gson();
                    String userString = gson.toJson(myUser);
                    editor.putString(String.valueOf(R.string.SharedPreferenceUserKey),userString);
                    editor.commit();

                    displayAlertDialog("Registeration is successfully completed!","Success!",1);
                }

               /* Intent intent = new Intent();
                intent.putExtra("key2","Ajay");
                setResult(2,intent);
                finish();*/

            }
        });
    }

    public void displayAlertDialog(String message, String title, final int tagg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              if(tagg == 0){

              }else{
                  Intent regiseterIntent = new Intent(getApplicationContext(),LoginActivity.class);
                  startActivity(regiseterIntent);
              }
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
