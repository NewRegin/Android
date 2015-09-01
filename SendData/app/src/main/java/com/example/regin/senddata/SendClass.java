package com.example.regin.senddata;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.StaticLayout;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Regin on 15/5/3.
 */
//public class SendClass implements Serializable {
public  class SendClass implements Parcelable {
    private String name;
    private Bundle bl;
    private int age;

    public Bundle getBl() {
        return bl;
    }

    public void setBl(Bundle bl) {
        this.bl = bl;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public SendClass(Bundle bl,int age){
       // this.name=name;
        this.bl=bl;
        this.age=age;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //   dest.writeString(getName());
           dest.writeBundle(bl);
           dest.writeInt(getAge());
    }

    public static final Creator<SendClass> CREATOR= new Creator<SendClass>() {
            @Override
            public SendClass createFromParcel(Parcel source) {
               Bundle bl2 =new Bundle(source.readBundle());
                return new SendClass(bl2, source.readInt());
            }


            @Override
            public SendClass[] newArray(int size) {
                return new SendClass[size];
            }
        };

}
