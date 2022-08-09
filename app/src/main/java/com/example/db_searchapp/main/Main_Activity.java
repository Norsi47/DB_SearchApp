package com.example.db_searchapp.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_searchapp.R;

import DataBase.DataBaseInfo;

public class Main_Activity extends AppCompatActivity {
    //calling in all the buttons, text view and lists used in the UI app
    //match variable names to id names
    Button buttonViewUser, addUserButton;
    //use this for Plain Text palette
    EditText fNameID, LNameID, ageID, addyID;

    RecyclerView recyclerViewUsersInfo;
    RecyclerView.LayoutManager recycleLayoutManager;
    Switch activeSwitch;

    ArrayAdapter arrayAdapter;
    DataBaseInfo dataBaseInfo;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //sets the content in main_activity.xml
        setContentView(R.layout.main_activity);

        //assign ids in UI to variable names
        buttonViewUser = findViewById(R.id.buttonViewUser);
        addUserButton = findViewById(R.id.addUserButton);
        fNameID = findViewById(R.id.fNameID);
        LNameID = findViewById(R.id.LNameID);
        ageID = findViewById(R.id.ageID);
        addyID = findViewById(R.id.addyID);
        recyclerViewUsersInfo = findViewById(R.id.recyclerViewUsersInfo);

        dataBaseInfo = new DataBaseInfo(Main_Activity.this);


        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



//    public void showCustomerOnRecyclerList(DataBaseInfo dataBaseInfo1) {
//        arrayAdapter = new ArrayAdapter<UsersInfo>(Main_Activity.this,
//                android.R.layout.simple_expandable_list_item_1, dataBaseInfo1. )
//
//    }



}
