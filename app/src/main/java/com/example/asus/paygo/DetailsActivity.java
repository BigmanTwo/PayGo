package com.example.asus.paygo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.paygo.config.Fruits;
import com.example.asus.paygo.sql.MyFruitsSql;
import com.example.asus.paygo.sql.MySqlOpen;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImageView;
    private TextView mTextView1,mTextView2;
    private Button mButton1,mButton2;
    private  Fruits fruits;
    private MyFruitsSql myFruitsSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MySqlOpen open=new MySqlOpen(this,"Fruits",null,1);
        SQLiteDatabase db=open.getWritableDatabase();
         myFruitsSql=new MyFruitsSql(db);
        mImageView=(ImageView)findViewById(R.id.details_image);
        mTextView1=(TextView)findViewById(R.id.details_name);
        mTextView2=(TextView)findViewById(R.id.details_price);
        mButton1=(Button)findViewById(R.id.shoping);
        mButton2=(Button)findViewById(R.id.shoping_order);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        Intent intent=getIntent();
       Bundle bundle=intent.getExtras();
       fruits= (Fruits) bundle.getSerializable("fruit");
        mImageView.setImageResource(fruits.getImage());
        mTextView1.setText(fruits.getName());
        mTextView2.setText(fruits.getPrice()+"");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.shoping:
                add();
                break;
            case R.id.shoping_order:
                Intent intent=new Intent(this,OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("fruit",fruits);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
    private void add(){
        boolean b=myFruitsSql.selectName(fruits);
        if (b){
            myFruitsSql.add(fruits);
        }else{
            myFruitsSql.upDate(fruits);
        }

    }
}
