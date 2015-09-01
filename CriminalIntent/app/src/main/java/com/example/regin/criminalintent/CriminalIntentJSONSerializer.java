package com.example.regin.criminalintent;

import android.content.Context;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Regin on 15/6/7.
 */
public class CriminalIntentJSONSerializer {
    private Context context;
    private String filename;
    File file = null;

    public CriminalIntentJSONSerializer(Context c,String f){
        context = c;
        filename = f;

    }

    public void saveCrimes(ArrayList<Crime> crimes)throws JSONException,IOException {
        JSONArray jsonArray = new JSONArray();
       // Log.e("TAG",""+file.getAbsolutePath());
        for (Crime c : crimes) {
            jsonArray.put(c.toJSON());
        }

        Writer writer = null;

          try{
            OutputStream outputStream = context.
                    openFileOutput(filename,Context.MODE_PRIVATE);

//            OutputStream outputStream = new FileOutputStream(file);

              writer = new OutputStreamWriter(outputStream);
                 writer.write(jsonArray.toString());
            // Log.e("TTT","OK1");


         //   outputStream.close();
         //   Log.e("TTT", "OK");
        } finally {
            if (writer != null)
                writer.close();

        }
        }
    public ArrayList<Crime> loadCrimes()throws Exception{

        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader bufferedReader = null;

        try {
//                Log.e("TAG",""+file.getAbsolutePath());
//                InputStream inputStream = new FileInputStream(file);
            InputStream inputStream = context.openFileInput(filename);
            bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray jsonArray =
                    (JSONArray) new JSONTokener(jsonString.toString())
                            .nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                crimes.add(new Crime(jsonArray.getJSONObject(i)));
            }
        } finally {
            if (bufferedReader != null)
                bufferedReader.close();

        }

        return crimes;
    }
    }

