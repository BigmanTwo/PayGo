package com.example.asus.paygo.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus on 2016/5/2.
 */
public class MySqlOpen extends SQLiteOpenHelper {
    public MySqlOpen(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists fruits(_id integer primary key AUTOINCREMENT," +
                "image int,name varchar(20),price double,count integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
