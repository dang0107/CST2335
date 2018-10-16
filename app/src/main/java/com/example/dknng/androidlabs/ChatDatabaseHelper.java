package com.example.dknng.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabase";
    public static final int VERSION_NUM = 5;
    public static final String TABLE_NAME = "Message";
    public static final String KEY_MESSAGE ="KEY_MESSAGE" ;
    public static final String KEY_ID ="KEY_ID";


    public ChatDatabaseHelper(Context ctx){
        super(ctx,DATABASE_NAME,null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "KEY_MESSAGE text);");

        Log.i("ChatDatabaseHelper","Calling onCreate");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade,oldVersion" + oldVersion + "newVersion =" + newVersion);
    }


}


