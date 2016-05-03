package com.example.asus.paygo.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asus.paygo.config.Fruits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2016/5/2.
 */
public class MyFruitsSql {
    private SQLiteDatabase db;

    public MyFruitsSql(SQLiteDatabase db) {
        this.db = db;
    }
    public void add(Fruits fruit){
        db.execSQL("insert into fruits(image,name,price,count)values(?,?,?,？)",
                new Object[]{fruit.getImage(),fruit.getName(),fruit.getPrice(),1});
    }
    public List<Fruits> quuery(){
        List<Fruits> list=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from fruits",null);
        while (cursor.moveToNext()){
            int image=cursor.getInt(cursor.getColumnIndex("image"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            double price=cursor.getDouble(cursor.getColumnIndex("price"));
            int count=cursor.getInt(cursor.getColumnIndex("count"));
            Fruits fruits=new Fruits(image,name,price,count);
            list.add(fruits);
        }
        return list;
    }
    public boolean selectName(Fruits fruits){
        boolean b=true;
        Cursor cursor=db.rawQuery("select * from fruits where name='"+fruits.getName()+"'",null);
       while(cursor.moveToNext()){
           b=false;
           break;
       }
        return b;
    }
    public void upDate(Fruits fruits){
        Cursor cursor=db.rawQuery("select count from fruits where name='"+fruits.getName()+"'",null);
        while(cursor.moveToNext()) {
            fruits.setCount(cursor.getInt(cursor.getColumnIndex("count")));
            break;
        }
        db.execSQL("update fruits set count="+(fruits.getCount()+1)+" where name='"+fruits.getName()+"'");
    }
}
