package com.example.regin.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Regin on 15/6/10.
 */
public class Photo {
    private static final String JSON_FILENAME = "filename";
    private String filename;

    public String getFilename() {
        return filename;
    }
    public Photo(String s){
        filename = s;
    }
    public Photo(JSONObject json)throws JSONException{
        filename = json.getString(JSON_FILENAME);
    }
    public JSONObject toJSON() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_FILENAME,filename);
        return jsonObject;
    }
}
