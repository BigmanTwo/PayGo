package com.example.asus.paygo.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.paygo.R;
import com.example.asus.paygo.config.Fruits;

import java.util.List;

/**
 * Created by Asus on 2016/5/1.
 */
public class MyBaseAdpter extends BaseAdapter {
    private List<Fruits> list;
    private Context mContext;

    public MyBaseAdpter(List<Fruits> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler=null;
        if(convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.gridviewstyle,null);
            viewHodler.imageView=(ImageView)convertView.findViewById(R.id.gridview_image);
            viewHodler.textView1=(TextView)convertView.findViewById(R.id.gridview_text1);
            viewHodler.textView2=(TextView)convertView.findViewById(R.id.gridview_text2);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.imageView.setImageResource(list.get(position).getImage());
        viewHodler.textView1.setText(list.get(position).getName());
        viewHodler.textView2.setText(list.get(position).getPrice()+"");

        return convertView;
    }
    class ViewHodler{
       private  ImageView imageView;
        private TextView textView1,textView2;
    }
}
