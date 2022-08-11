package com.example.db_searchapp.main;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_searchapp.R;

import DataBase.DataBaseInfo;
import model.UsersInfo;

public class Main_Activity extends AppCompatActivity {
    //calling in all the buttons, text view and lists used in the UI app
    //match variable names to id names
    Button buttonViewUser, addUserButton;
    //use this for Plain Text palette
    EditText fName, lName, age, addy, phone;

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
        fName = findViewById(R.id.fNameID);
        lName = findViewById(R.id.LNameID);
        age = findViewById(R.id.ageID);
        addy = findViewById(R.id.addyID);
        phone = findViewById(R.id.phoneId);
        recyclerViewUsersInfo = findViewById(R.id.recyclerViewUsersInfo);

        dataBaseInfo = new DataBaseInfo(Main_Activity.this);


//        addUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });\
        /*the other way to do this
        *the (view) is goten from the view in line 50
        * click on -> to see where class goes*/
        addUserButton.setOnClickListener((view) -> {
            //did not initiate class here because we need to use it later in try and catch
            UsersInfo usersInfo;

            //try and catch to prevent app from giving error when creating User
            try {
                //gets the toString in UserInfo class
                //needs to match all variable names called in userInfo contructor
                usersInfo = new UsersInfo(-1, fName.getText().toString(), lName.getText().toString(), addy.getText().toString(),
                        Integer.parseInt(age.getText().toString()), Integer.parseInt(phone.getText().toString()), activeSwitch.isChecked());

                //will display when button is pressed
                //will print out the toString in users info
                Toast.makeText(Main_Activity.this, usersInfo.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(Main_Activity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                usersInfo = new UsersInfo(-1, "fName Error", "lName error",
                        "addy error", 0, 0, false);

            }
            DataBaseInfo dataBaseInfo = new DataBaseInfo(Main_Activity.this);
            //inset users info into data base
            boolean success = dataBaseInfo.addIntoDB(usersInfo);
            //print out if success
            Toast.makeText(Main_Activity.this, "Successfully= " + success, Toast.LENGTH_SHORT).show();

        });

    }



//the list view Ui side
    public void showCustomerOnRecyclerList(DataBaseInfo dataBaseInfo1) {
        arrayAdapter = new ArrayAdapter<UsersInfo>(Main_Activity.this,
                android.R.layout.simple_expandable_list_item_1, dataBaseInfo1.selectAllCustomer());


    }



}
