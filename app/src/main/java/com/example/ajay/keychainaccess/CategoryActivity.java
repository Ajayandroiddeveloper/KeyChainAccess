package com.example.ajay.keychainaccess;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ajay.fragments.EditFragmentActivity;
import com.example.ajay.models.Category;
import com.example.ajay.models.Item;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ArrayList<Category> categories = new ArrayList<Category>();
    // ArrayList<Item>items = new ArrayList<Item>();
    ListView listView;
    CustomAdapter adapter;
    EditFragmentActivity editFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        listView = (ListView) findViewById(R.id.category_listView);

       /* ArrayList<Item> items1 = new ArrayList<Item>();
        ArrayList<Item> items2 = new ArrayList<Item>();
        ArrayList<Item> items3 = new ArrayList<Item>();
        Item itemOne = new Item(R.drawable.money, "HDFC", "This is our first Bank", 8, null, "1234");
        Item itemTwo = new Item(R.drawable.money, "SBI", "This is our Bank", 30, null, "1434");
        Item itemThree = new Item(R.drawable.shoping, "Flipcart", "This is our first Shoping Site", 25, null, "1345");
        items1.add(itemOne);
        items2.add(itemTwo);
        items2.add(itemThree);*/


       /* Category categoryOne = new Category(R.drawable.money, "Banking", "this is Banking category", 5, items1);
        Category categoryTwo = new Category(R.drawable.shoping, "Shoping", "this is Shoping Category", 20, items2);
        categories.add(categoryOne);
        categories.add(categoryTwo);*/

        adapter = new CustomAdapter();
        listView.setAdapter(adapter);
        listViewItemClicked();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                Intent addCategoryIntent = new Intent(this, AddCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("SHOW_EDITTEXTS", false);
                addCategoryIntent.putExtras(bundle);
                startActivityForResult(addCategoryIntent, 1);


                break;
            case R.id.logout_menu:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Log.i("category", "category object came");
            Category category = (Category) data.getSerializableExtra("CATEGORY_OBJECT");
            Log.i("name", category.getName());
            Log.i("description", category.getDescription());
            Log.i("time", String.valueOf(category.getCreatedDateTime()));
            categories.add(category);
            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(categories != null) {
                return categories.size();
            }
            return 0;
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
            Button editButton = (Button) convertView.findViewById(R.id.edit_btn);
            editButton.setTag(position);
            Button delButton = (Button) convertView.findViewById(R.id.delete_btn);
            delButton.setTag(position);

            registerEditButton(editButton);
            registerDeleteButton(delButton);

            Category c = categories.get(position);
            imageView.setImageResource(c.getImageValue());
            nameTextView.setText(c.getName());
            descTextView.setText(c.getDescription());

            return convertView;
        }

        public void registerEditButton(final Button editButton) {
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Edit", "pressed");
                   /* FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    EditFragmentActivity mFragment = new EditFragmentActivity();
                    ft.add(R.id.activity_category,mFragment);
                    ft.commit();
                   Category selectedCategory = categories.get((Integer) editButton.getTag());
                    mFragment.setmFragmentName(selectedCategory.getName());
                    mFragment.setmFragmentDescription(selectedCategory.getDescription());*/
                }
            });
        }

        public void registerDeleteButton(final Button deleteButton) {
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Delete", "Pressed");
                    AlertDialog.Builder deleteAlertDialog = new AlertDialog.Builder(v.getContext());
                    deleteAlertDialog.setTitle("Delete");
                    deleteAlertDialog.setMessage("Are you sure want to delete this Category");
                    deleteAlertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int buttonTag = (int) deleteButton.getTag();
                            categories.remove(buttonTag);
                            adapter.notifyDataSetChanged();
                        }
                    });

                    deleteAlertDialog.setNegativeButton("Cancle", null);
                    AlertDialog alertDialog = deleteAlertDialog.create();
                    alertDialog.show();
                }
            });

        }
    }

    public void listViewItemClicked() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemIntent = new Intent(getApplicationContext(), ItemActivity.class);
                Category selectedCategory = categories.get(position);
                ArrayList<Item> categoryItems = (ArrayList<Item>) selectedCategory.getItems();
                itemIntent.putParcelableArrayListExtra("ITEMS", categoryItems);
                startActivity(itemIntent);
            }
        });
    }

}

