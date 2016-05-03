package com.example.asus.paygo.config;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Asus on 2016/5/1.
 */
public class Fruits implements Serializable,Parcelable {
    private int image;
    private String name;
    private double price;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Fruits(int image, String name, double price, int count) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.count=count;
    }
    protected Fruits(Parcel in) {
        image = in.readInt();
        name = in.readString();
        price = in.readDouble();
        count=in.readInt();
    }

    public static final Creator<Fruits> CREATOR = new Creator<Fruits>() {
        @Override
        public Fruits createFromParcel(Parcel in) {
            return new Fruits(in);
        }

        @Override
        public Fruits[] newArray(int size) {
            return new Fruits[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Fruits(int image, String name, double price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(count);
    }
}
