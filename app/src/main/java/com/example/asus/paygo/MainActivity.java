package com.example.asus.paygo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.paygo.adpter.MyBaseAdpter;
import com.example.asus.paygo.adpter.MyPagerAdpter;
import com.example.asus.paygo.config.Fruits;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener,AdapterView.OnItemClickListener{
    private EditText edit_user,edit_psw;
    private Button mButton;
    private ViewPager mViewPager;
    private ViewGroup mViewGroup;
    private int[] images={R.mipmap.apple,R.mipmap.grapefruit,R.mipmap.greenanpple,R.mipmap.kiwi,R.mipmap.orange};
    private GridView mGridView;
    private ImageView[] mImageView,tips;
    static final String FIRST="first";
    static final String PRESS="MyAdpter";
    static final String NAME="user";
    private boolean first;
    private List<Fruits> mList;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 1:
                int index = mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(index + 1);
                mHandler.sendEmptyMessageDelayed(1, 2000);
                    break;
                case 2:
                    ArrayList list=msg.getData().getParcelableArrayList("list");

                    mList= (List<Fruits>) list.get(0);
                    Log.d("handler",mList.size()+"");
                    MyBaseAdpter baseAdpter=new MyBaseAdpter(mList,MainActivity.this);
                    mGridView.setAdapter(baseAdpter);
                    mGridView.setOnItemClickListener(MainActivity.this);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager=(ViewPager)findViewById(R.id.scanner_pagers);
        mViewGroup=(ViewGroup)findViewById(R.id.viewgroup);
        mGridView=(GridView)findViewById(R.id.gridview);
        SharedPreferences preferences=getSharedPreferences(PRESS,MODE_PRIVATE);
        first=preferences.getBoolean(FIRST,true);
        if(first){
        login();
            Toast.makeText(this,"第一次进来",Toast.LENGTH_SHORT).show();
        }else{
            innitViewPager();
            setList();
            new Thread(){
                @Override
                public void run() {
               Message message=mHandler.obtainMessage();
                    message.what=2;
                    ArrayList list=new ArrayList();
                    list.add(mList);
                    Bundle bundle=new Bundle();
                    bundle.putParcelableArrayList("list",list);
                    message.obj=mList;
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                }
            }.start();

            Toast.makeText(this,"不是第一次进来",Toast.LENGTH_SHORT).show();
        }
    }
    private void setList(){
        mList=new ArrayList<>();
        mList.add(new Fruits(R.mipmap.kiwi,"水蜜桃",12.3));
        mList.add(new Fruits(R.mipmap.apple,"苹果",5.3));
        mList.add(new Fruits(R.mipmap.greenanpple,"青苹果",13.0));
        mList.add(new Fruits(R.mipmap.orange,"橘子",5.5));
        mList.add(new Fruits(R.mipmap.strawberry,"草莓",25.6));
        mList.add(new Fruits(R.mipmap.peper,"水果椒",4.6));
        mList.add(new Fruits(R.mipmap.greenanpple,"青苹果",13.0));
        mList.add(new Fruits(R.mipmap.kiwi,"水蜜桃",12.3));
        mList.add(new Fruits(R.mipmap.strawberry,"草莓",25.6));
        mList.add(new Fruits(R.mipmap.kiwi,"水蜜桃",12.3));

    }
    private void login(){
        edit_user=(EditText)findViewById(R.id.user_text);
        edit_psw=(EditText)findViewById(R.id.password_text);
        mButton=(Button)findViewById(R.id.login_button);
        mButton.setOnClickListener(this);
        SharedPreferences sharedPreferences=getSharedPreferences(PRESS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(FIRST,false);
        editor.commit();
    }
    private void innitViewPager(){
        getImageView();
        getTips();
        MyPagerAdpter pagerAdpter=new MyPagerAdpter(mImageView,this);
        mViewPager.setAdapter(pagerAdpter);
        mViewPager.setCurrentItem(mImageView.length*100);
        mViewPager.setOnPageChangeListener(this);
        mHandler.sendEmptyMessageDelayed(1,2000);

    }
    private void getImageView(){
        mImageView=new ImageView[images.length];
        for(int i=0;i<images.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            mImageView[i]=imageView;
        }
    }
    private void getTips(){
        tips=new ImageView[images.length];
        for (int i=0;i<images.length;i++){
            ImageView imageView=new ImageView(this);
            tips[i]=imageView;
            if(i==0){
                tips[i].setBackgroundResource(R.mipmap.dui);
            }else{
                tips[i].setBackgroundResource(R.mipmap.fu);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            params.rightMargin=5;
            params.leftMargin=5;
            mViewGroup.addView(tips[i],params);
        }
    }
    private void selectItem(int item){
        for(int i=0;i<tips.length;i++){
            if(i==item){
                tips[i].setBackgroundResource(R.mipmap.dui);
            }else{
                tips[i].setBackgroundResource(R.mipmap.fu);
            }
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            selectItem(position%mImageView.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.setComponent(new ComponentName(this,DetailsActivity.class));
        Bundle bundle=new Bundle();
        bundle.putSerializable("fruit",mList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
