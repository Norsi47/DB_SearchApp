package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DataBaseInfo  extends SQLiteOpenHelper {

    public static final String DB_Name = "UsersInfo_DB.db";
    public static final int DB_VersionNum = 1;
    public static final String COLUMN_USERS_INFO = "USERS_INFO";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_ACTIVE_USER = "ACTIVE_USER";

    //will run everytime is called
    public DataBaseInfo(@Nullable Context context) {
        super(context, DB_Name, null, DB_VersionNum);
    }

    //is called everytime database is accessed
    //will help app re enter saved data into db file
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE " + COLUMN_USERS_INFO + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_PHONE + " INT, " + COLUMN_ACTIVE_USER + " BOOL) ";

        //execute the sql command (changed the colors to match sql)
       sqLiteDatabase.execSQL(createTableSQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
