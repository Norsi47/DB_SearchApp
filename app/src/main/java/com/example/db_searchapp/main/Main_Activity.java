package com.example.db_searchapp.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db_searchapp.R;

import DataBase.DataBaseInfo;
import model.UsersInfo;

public class Main_Activity extends AppCompatActivity {
    //calling in all the buttons, text view and lists used in the UI app
    //match variable names to id names
    Button buttonViewUser, addUserButton;
    //use this for Plain Text palette
    EditText firstName, lastName, age, address, phone;

    ListView recyclerViewUsersInfo;//List view more appropriate for showing added info

    Switch isUserActive;

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
        firstName = findViewById(R.id.fNameID);
        lastName = findViewById(R.id.LNameID);
        age = findViewById(R.id.ageID);
        address = findViewById(R.id.addyID);
        phone = findViewById(R.id.phoneId);
        recyclerViewUsersInfo = findViewById(R.id.listView);

        dataBaseInfo = new DataBaseInfo(Main_Activity.this);

        //calling in show list when clicked
        showCustomerOnRecyclerList(dataBaseInfo);


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
                usersInfo = new UsersInfo(-1, firstName.getText().toString(), lastName.getText().toString(), address.getText().toString(),
                        Integer.parseInt(age.getText().toString()), Integer.parseInt(phone.getText().toString()), isUserActive.isChecked());

                //will display when button is pressed
                //will print out the toString in users info
                Toast.makeText(Main_Activity.this, usersInfo.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
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

        //viewall button logic
        buttonViewUser.setOnClickListener((view)-> {
            //when pressed show user in database
            DataBaseInfo dataBaseInfo = new DataBaseInfo(Main_Activity.this);
            showCustomerOnRecyclerList(dataBaseInfo);

        });


        //what will happen when user info is clicked on list
        recyclerViewUsersInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UsersInfo usersInfo = (UsersInfo) adapterView.getItemAtPosition(i);
                dataBaseInfo.deleteUser(usersInfo);
                showCustomerOnRecyclerList(dataBaseInfo);
                Toast.makeText(Main_Activity.this, "Deleted " + usersInfo.toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }


    //the list view Ui side
    public void showCustomerOnRecyclerList(DataBaseInfo dataBaseInfo1) {
        arrayAdapter = new ArrayAdapter<UsersInfo>(Main_Activity.this,
                android.R.layout.simple_expandable_list_item_1, dataBaseInfo1.selectAllCustomer());
        recyclerViewUsersInfo.setAdapter(arrayAdapter);//this only works on ListView and not recycleview
    }


}
