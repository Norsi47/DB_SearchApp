package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.UsersInfo;


public class DataBaseInfo  extends SQLiteOpenHelper {

    public static final String DB_Name = "UsersInfo_DB.db";
    public static final int DB_VersionNum = 1;
    public static final String USERS_INFO_TABLE = "USERS_INFO";   //table name
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_ACTIVE_USER = "ACTIVE_USER";
    public static final String COLUMN_USER_AGE = "USER_AGE";

    //will run everytime is called
    public DataBaseInfo(@Nullable Context context) {
        super(context, DB_Name, null, DB_VersionNum);
    }

    //is called everytime database is accessed
    //will help app re enter saved data into db file
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE " + USERS_INFO_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_PHONE + " INT, " + COLUMN_USER_AGE + " INT, " + COLUMN_ACTIVE_USER + " BOOL) ";

        //execute the sql command (changed the colors to match sql)
       sqLiteDatabase.execSQL(createTableSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //get info from model class(user info) then put it into database

    public boolean addIntoDB(UsersInfo usersInfo) {
        //writable db because it is (writing) info
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //gets contents from model class
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, usersInfo.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, usersInfo.getLastName());
        contentValues.put(COLUMN_ADDRESS, usersInfo.getAddress());
        contentValues.put(COLUMN_PHONE, usersInfo.getPhone());
        contentValues.put(COLUMN_USER_AGE, usersInfo.getAge());
        contentValues.put(COLUMN_ACTIVE_USER, usersInfo.isUserActive());
        //id is auto made in data base

        long insertDB = sqLiteDatabase.insert(USERS_INFO_TABLE, null, contentValues);
        //if positive return true else false
        if (insertDB == -1) {
            return false;
        } else {
            return true;
        }
    }

    //method to get all customer
    public List<UsersInfo> selectAllCustomer() {
        List<UsersInfo> usersInfoList = new ArrayList<>();
        //query to select all data from the table

        String selectQueryString = "SELECT * FROM " + USERS_INFO_TABLE;
        /*using readable because it is selecting and not doing writing or deleting*/
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //how it moves in the database
        Cursor cursor = sqLiteDatabase.rawQuery(selectQueryString, null);
        //logic to move it
        if (cursor.moveToFirst()) {
            do {
                //following all the columns in database table
                int userId = cursor.getInt(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                String address = cursor.getString(3);
                int phone = cursor.getInt(4);
                int age = cursor.getInt(5);
                boolean activeUser = cursor.getInt(6) == 1 ? true: false;

                UsersInfo usersInfo = new UsersInfo(userId, firstName, lastName, address, phone, age, activeUser);
                //put all this into the usersInfo empy list
                usersInfoList.add(usersInfo);
            }
            while (cursor.moveToNext());
        }
        else {

        }
        //closes the cursor when db is done
        cursor.close();
        sqLiteDatabase.close();
        return usersInfoList;

    }

}
