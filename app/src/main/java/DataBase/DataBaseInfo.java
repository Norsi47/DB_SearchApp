package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import model.UsersInfo;


public class DataBaseInfo  extends SQLiteOpenHelper {

    public static final String DB_Name = "UsersInfo_DB.db";
    public static final int DB_VersionNum = 1;
    public static final String COLUMN_USERS_INFO = "USERS_INFO";   //table name
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
        String createTableSQL = "CREATE TABLE " + COLUMN_USERS_INFO + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRST_NAME + " TEXT, " +
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

        long insertDB = sqLiteDatabase.insert(COLUMN_USERS_INFO, null, contentValues);
        //if positive return true else false
        if (insertDB == -1) {
            return false;
        } else {
            return true;
        }
    }

}
