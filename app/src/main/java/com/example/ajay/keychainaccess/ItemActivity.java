package com.example.ajay.keychainaccess;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ajay.models.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity{

    ArrayList<Item> items = new ArrayList<Item>();
    ListView itemListView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        itemListView = (ListView) findViewById(R.id.item_listView);

        ArrayList<Item> myItems = getIntent().getParcelableArrayListExtra("ITEMS");
        if(myItems != null) {
            items = myItems;
        }


        adapter = new CustomAdapter();
        itemListView.setAdapter(adapter);
        listViewItemClicked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem_menu:
                Intent addCategoryIntent = new Intent(this, AddCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("SHOW_EDITTEXTS", true);
                addCategoryIntent.putExtras(bundle);
                startActivityForResult(addCategoryIntent,2);


                break;
        }

        return true;
    }

    public void listViewItemClicked() {

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemIntent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                Item selectedItem = items.get(position);
                itemIntent.putExtra("SELECTED ITEM",(Parcelable) selectedItem);
                startActivity(itemIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2){
         Item item = data.getParcelableExtra("ITEM");
            Log.i("1",String.valueOf(item));
            items.add(item);
            adapter.notifyDataSetChanged();

        }
    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(items == null){
                return  0;
            }
            return items.size();
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_category_item, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_iv);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name_tv);
            TextView descTextView = (TextView) convertView.findViewById(R.id.description_tv);
            Button editButton = (Button)convertView.findViewById(R.id.edit_btn);
            editButton.setTag(position);
            Button deleteButton = (Button)convertView.findViewById(R.id.delete_btn);
            deleteButton.setTag(position);

            registerEditButtonPressed(editButton);
            registerDeleteButtonPressed(deleteButton);

            Item i = items.get(position);
            imageView.setImageResource(i.getImageValue());
            Log.i("imageresource",String.valueOf(i.getImageValue()));
            nameTextView.setText(i.getName());
            descTextView.setText(i.getDescription());
            return convertView;
        }

        public void registerEditButtonPressed(Button editButton){
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void registerDeleteButtonPressed(final Button deleteButton){
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Delete");
                    builder.setMessage("Are you sure want to delete this item");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int deleteItem = (int)deleteButton.getTag();
                            items.remove(deleteItem);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Cancle",null);
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });
        }
    }
}
