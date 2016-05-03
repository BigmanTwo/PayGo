package com.example.asus.paygo.adpter;

import android.content.Context;
import android.support.annotation.Size;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Asus on 2016/5/1.
 */
public class MyPagerAdpter extends PagerAdapter {
    private ImageView[] imageViews;
    private Context mContext;

    public MyPagerAdpter(ImageView[] imageViews, Context mContext) {
        this.imageViews = imageViews;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(imageViews[position%imageViews.length]);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(imageViews[position%imageViews.length]);
        return imageViews[position%imageViews.length];
    }
}
