package com.example.tektak.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tektak on 10/9/15.
 */
public class DBRegistLogin extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "InfoRegistLogin.db";

    public static final String INFO_TABLE_NAME = "Info";
    public static final String INFO_COLUMN_ID = "id";
    public static final String INFO_COLUMN_USERNAME = "username";
    public static final String INFO_COLUMN_PASSWORD = "password";
    public static final String INFO_COLUMN_EMAIL = "email";
    public static final String INFO_COLUMN_NAME = "name";
    public static final String INFO_COLUMN_RELATIONSHIP = "relationship";
    public static final String INFO_COLUMN_GENDER = "gender";
    public static final String INFO_COLUMN_CHILDNAME = "childname";
    public static final String INFO_COLUMN_PHOTO1 = "photo1";
    public static final String INFO_COLUMN_PHOTO2 = "photo2";

//    public static final String STATUS_TABLE_NAME = "Status";
//    public static final String STATUS_COLUMN_ID = "id";
//    public static final String STATUS_LOGIN ="login_status";

    public DBRegistLogin(Context context){
        super(context, DATABASE_NAME, null, 1);
//        context.deleteDatabase(DATABASE_NAME); //Use this when you have to delete the database!!
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Info " + "(id integer primary key, username text,password text, name text, relationship text, childname text, photo1 text, photo2 text, email text, gender text)");
//        db.execSQL("create table Status " + "(id integer primary key, login_status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Info");
        onCreate(db);
    }

    //this should be executed when regiteration is carried out
    public boolean insertData(String username, String password, String name, String relationship, String childname, String pathToPhoto1, String pathToPhoto2, String email, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        ContentValues contentValues1 = new ContentValues();
//        String status_value = "0";
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("relationship", relationship);
        contentValues.put("childname", childname);
        contentValues.put("photo1", pathToPhoto1);
        contentValues.put("photo2", pathToPhoto2);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
//        contentValues1.put("login_status", status_value);

        db.insert("Info", null, contentValues);
//        db.insert("Status", null, contentValues1);
        return true;
    }

    public boolean checkUserNamePasW(String username, String password){
        boolean flag = false;
        String user_name, pass_word;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Info", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            user_name = res.getString(res.getColumnIndex(INFO_COLUMN_USERNAME));
            pass_word = res.getString(res.getColumnIndex(INFO_COLUMN_PASSWORD));
            if (username.equals(user_name) && password.equals(pass_word)) {
                flag = true;
                break;
            }
            res.moveToNext();
        }
        return flag;
    }

//    public boolean login_status(Boolean flaggy){
//        boolean flag = false;
//        String column_id;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery("select * from Status", null);
//        res.moveToFirst();
//        column_id = res.getString(res.getColumnIndex(STATUS_COLUMN_ID));
//
//        SQLiteDatabase db1 = this.getWritableDatabase();
//        if(flaggy == true){
//            String strSQL = "UPDATE Status SET login_status = 1 WHERE columnId = "+ column_id;
//            db1.execSQL(strSQL);
//        }
//        else{
//            String strSQL = "UPDATE Status SET login_status = 0 WHERE columnId = "+ column_id;
//            db1.execSQL(strSQL);
//        }
//        return flag;
//    }
//
//    public boolean check_login_status(){
//        boolean flag = false;
//        String login_status;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery("select * from Status", null);
//        res.moveToFirst();
//        login_status = res.getString(res.getColumnIndex(STATUS_LOGIN));
//        if(login_status.equals("0"))
//            flag = false;
//        else
//            flag = true;
//        return flag;
//    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, INFO_TABLE_NAME);
        return numRows;
    }

    public List<String> getPathToPhoto(String username){
        List<String> paths = new ArrayList<>();
        String user_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Info", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            user_name = res.getString(res.getColumnIndex(INFO_COLUMN_USERNAME));
//            pass_word = res.getString(res.getColumnIndex(INFO_COLUMN_PASSWORD));
            if (username.equals(user_name)) {
                paths.add(res.getString(res.getColumnIndex(INFO_COLUMN_PHOTO1)));
                paths.add(res.getString(res.getColumnIndex(INFO_COLUMN_PHOTO2)));
//                break;
            }
            res.moveToNext();
        }
        return paths;
    }

    public String getChildName(String username){
        String childName="";
        String user_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Info", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            user_name = res.getString(res.getColumnIndex(INFO_COLUMN_USERNAME));
            if (username.equals(user_name)) {
                childName = res.getString(res.getColumnIndex(INFO_COLUMN_CHILDNAME));
            }
            res.moveToNext();
        }
        return childName;
    }
}
