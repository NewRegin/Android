package com.example.regin.criminalintent;

import android.text.format.Time;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;
import java.util.jar.JarException;

/**
 * Created by Regin on 15/5/8.
 */
public class Crime {
    private UUID mID;
    private String mTitle;
    private Date date;
    private boolean solved;
    private String name;

    private Photo photo;
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_NAME = "name";

//    private Time time;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Date getDate() {

        return date;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public void setDate(Date data) {
        this.date = data;
    }

    public UUID getmID() {
        return mID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Crime(){
        mID = UUID.randomUUID();
        date = new Date();
    }
    public Crime(JSONObject jsonObject)throws Exception{
        mID = UUID.fromString(jsonObject.getString(JSON_ID));
        date = new Date(jsonObject.getLong(JSON_DATE));
        solved = jsonObject.getBoolean(JSON_SOLVED);
        mTitle = jsonObject.getString(JSON_TITLE);
        if(jsonObject.has(JSON_NAME)){
            name = jsonObject.getString(JSON_NAME);
        }
        if (jsonObject.has(JSON_PHOTO)){
            photo = new Photo(jsonObject.getJSONObject(JSON_PHOTO));
        }
    }
    public JSONObject toJSON()throws JSONException{
        JSONObject js = new JSONObject();
        js.put(JSON_ID,mID.toString());
        js.put(JSON_DATE,date.getTime());
        js.put(JSON_SOLVED,solved);
        js.put(JSON_TITLE,mTitle);
        if(name != null){
            js.put(JSON_NAME,name);
        }
        if (photo != null){
            js.put(JSON_PHOTO,photo.toJSON());
        }
        return js;
    }
}
