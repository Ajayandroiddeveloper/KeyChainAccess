package com.example.ajay.keychainaccess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ajay.models.Category;
import com.example.ajay.models.Item;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText mAddName;
    private EditText mAddDescription;
    private EditText mAddPassword;
    private EditText mAddConfirmPassword;
    private Button mAddButton;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        mAddName = (EditText)findViewById(R.id.catName_et);
        mAddDescription = (EditText)findViewById(R.id.catDescription_et);
        mAddPassword = (EditText)findViewById(R.id.catPassword_et);
        mAddConfirmPassword = (EditText)findViewById(R.id.catConfirmPassword_et);
        mAddButton = (Button)findViewById(R.id.catAdd_btn);

        addButtonPressed();

         flag =  getIntent().getExtras().getBoolean("SHOW_EDITTEXTS");

        if (flag) {
            Log.i("Item Activity",String.valueOf(flag)) ;
            mAddPassword.setVisibility(View.VISIBLE);
            mAddConfirmPassword.setVisibility(View.VISIBLE);
            setTitle("Add Item");
        }else {
            Log.i("Category Activity",String.valueOf(flag)) ;
            mAddPassword.setVisibility(View.INVISIBLE);
            mAddConfirmPassword.setVisibility(View.INVISIBLE);
            setTitle("Add Category");
        }
    }

    public void addButtonPressed(){
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mAddName.getText().toString();
                String description = mAddDescription.getText().toString();
                String password = mAddPassword.getText().toString();
                String confirmPassword = mAddConfirmPassword.getText().toString();
                  if(flag){

                      //For Item Code

                      if (!name.isEmpty() && !description.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password.equals(confirmPassword)){

                          //displayAlertDialog("Your Items Add successfully","Success",1);
                          Item item = new Item(R.drawable.category,name,description,System.currentTimeMillis(),password);
                          Intent itemIntent = new Intent(getApplicationContext(),ItemActivity.class);
                          itemIntent.putExtra("ITEM",(Parcelable) item);
                          setResult(2,itemIntent);
                          finish();

                          return;
                      }else {

                          if(name.isEmpty()){
                              displayAlertDialog("Name should not be empty","Invalid",0);
                          }else if(description.isEmpty()){
                              displayAlertDialog("Description should not be empty","Invalid",0);

                          }else if(password.isEmpty()){
                              displayAlertDialog("Password should not be empty","Invalid",0);

                          }else if(confirmPassword.isEmpty()){
                              displayAlertDialog("Confirmpassword should not be empty","Invalid",0);

                          }else{
                              displayAlertDialog("Password & Confirmpassword are not matching","Invalid",0);
                          }
                      }

                    //For Category code
                  }else{
                      if (!name.isEmpty() && !description.isEmpty()){
                          //displayAlertDialog("Your Category Add Successfully","Success",1);
                          Category newCategory = new Category(R.drawable.category,name,description,System.currentTimeMillis(),null);
                             Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
                          intent.putExtra("CATEGORY_OBJECT",newCategory);
                          setResult(1,intent);
                          finish();
                      }
                      else {
                          if (name.isEmpty()){
                              displayAlertDialog("Name should not be empty","Error",0);
                          }else if (description.isEmpty()){
                              displayAlertDialog("Description should not be empty","Error",0);
                          }
                      }
                  }
            }
        });
    }

    public void displayAlertDialog(String message, String title, final int tagg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(AddCategoryActivity.this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(tagg == 0){

                }else{
                }
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
