package com.example.ajay.fragments;

import android.content.Context;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.ajay.keychainaccess.R;

public class EditFragmentActivity extends Fragment {

    View editFragmentView;
    EditText mFragmentName;
    EditText mFragmentDescription;
    Button mUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View fragmentView = inflater.inflate(R.layout.activity_edit_fragment,container,false);
        Log.i("View",String.valueOf(fragmentView));
       editFragmentView = fragmentView;
        mFragmentName = (EditText)editFragmentView.findViewById(R.id.nameFragment_et);

       // mFragmentDescription = (EditText) editFragmentView.findViewById(R.id.descriptionFragment_et);
        //mUpdate = (Button)editFragmentView.findViewById(R.id.updateFragment_btn);

        return  fragmentView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setmFragmentName(String name){
       mFragmentName.setText(name);
    }

    public void setmFragmentDescription(String description){
       mFragmentDescription.setText(description);
    }
}
