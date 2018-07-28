package com.example.tektak.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tektak on 1/2/16.
 */
public class DBStatusLogin extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LoginStatus.db";

    public static final String STATUS_TABLE_NAME = "Status";
    public static final String STATUS_COLUMN_ID = "id";
    public static final String STATUS_LOGIN ="login_status";
    public static final String STATUS_REGIST_USERNAME="username";

    private DBRegistLogin db1;

    public DBStatusLogin(Context context){
        super(context, DATABASE_NAME, null, 1);
//        context.deleteDatabase(DATABASE_NAME); //Use this when you have to delete the database!!
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Status " + "(id integer primary key, login_status text, username text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Status");
        onCreate(db);
    }

    //this should be executed when registration is carried out
    public boolean insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String status_value = "0";
        contentValues.put("login_status", status_value);
        db.insert("Status", null, contentValues);
        return true;
    }


    public boolean login_status(String username){
        boolean flag = false;
        String column_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Status", null);
        res.moveToLast();
        column_id = res.getString(res.getColumnIndex(STATUS_COLUMN_ID));

        Log.d("Column_id", column_id);

        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String status_value = "1";
        cv.put("login_status", status_value);
        cv.put("username", username);
        db2.update("Status", cv, "id="+column_id, null);

        return flag;
    }

    public boolean login_status_signOut(){
        boolean flag = false;
        String column_id="0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Status", null);
        res.moveToLast();
        Log.d("hiFromSignOutbefore", column_id);
        column_id = res.getString(res.getColumnIndex(STATUS_COLUMN_ID));

        Log.d("hiFromSignOut_columnId", column_id);

        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String status_value = "0";
        cv.put("login_status", status_value);
        db2.update("Status", cv, "id=" + column_id, null);
        return flag;
    }

    public boolean check_login_status(){
        boolean flag = false;
        String login_status="0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from Status", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            login_status = res.getString(res.getColumnIndex(STATUS_LOGIN));
            res.moveToNext();
        }
//        login_status = res.getString(res.getColumnIndex(STATUS_LOGIN));
        if(login_status.equals("0"))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public String getUserName(){
        String user_name="0";
        String test_status_login;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery("select * from Status", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            user_name = res.getString(res.getColumnIndex(STATUS_REGIST_USERNAME));
            test_status_login = res.getString(res.getColumnIndex(STATUS_LOGIN));
            res.moveToNext();
            Log.d("hello", test_status_login);
            Log.d("helloUsername", user_name);

        }

        Log.d("helloUserNameoutside", "I am here");
        return user_name;
    }

//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, INFO_TABLE_NAME);
//        return numRows;
//    }
//
//    public List<String> getPathToPhoto(String username){
//        List<String> paths = new ArrayList<>();
//        String user_name;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from Info", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            user_name = res.getString(res.getColumnIndex(INFO_COLUMN_USERNAME));
////            pass_word = res.getString(res.getColumnIndex(INFO_COLUMN_PASSWORD));
//            if (username.equals(user_name)) {
//                paths.add(res.getString(res.getColumnIndex(INFO_COLUMN_PHOTO1)));
//                paths.add(res.getString(res.getColumnIndex(INFO_COLUMN_PHOTO2)));
////                break;
//            }
//            res.moveToNext();
//        }
//        return paths;
//    }
}
