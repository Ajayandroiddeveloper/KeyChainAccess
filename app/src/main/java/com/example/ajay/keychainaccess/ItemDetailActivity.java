package com.example.ajay.keychainaccess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ajay.models.Item;

public class ItemDetailActivity extends AppCompatActivity {

    TextView mNameTextView;
    TextView mPasswordTextView;
    TextView mDescriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mNameTextView = (TextView)findViewById(R.id.nameLable_tv);
        mPasswordTextView = (TextView)findViewById(R.id.passwordLable_tv);
        mDescriptionTextView = (TextView)findViewById(R.id.descriptionLable_tv);

        Item selectedItem = getIntent().getParcelableExtra("SELECTED ITEM");
        if(selectedItem != null){
            mNameTextView.setText(selectedItem.getName());
            mPasswordTextView.setText(selectedItem.getPassword());
            mDescriptionTextView.setText(selectedItem.getDescription());
        }
    }
}
