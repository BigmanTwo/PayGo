package com.example.asus.paygo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asus.paygo.adpter.MyBaseAdpter;
import com.example.asus.paygo.adpter.MyOrderAdpter;
import com.example.asus.paygo.config.Fruits;
import com.example.asus.paygo.sql.MyFruitsSql;
import com.example.asus.paygo.sql.MySqlOpen;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private ListView mListView;
    private MyFruitsSql myFruitsSql;
    private List<Fruits> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        MySqlOpen open=new MySqlOpen(this,"Fruits",null,1);
        SQLiteDatabase db=open.getWritableDatabase();
        myFruitsSql=new MyFruitsSql(db);
        mListView=(ListView)findViewById(R.id.order_listview);
        quuery();
    }
    private void quuery(){
        mList=new ArrayList<>();
        mList=myFruitsSql.quuery();
        MyOrderAdpter adpter=new MyOrderAdpter(mList,this);
        mListView.setAdapter(adpter);
    }
}
