package com.example.regin.criminalintent;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Regin on 15/5/11.
 */
public class CrimeLab {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "Crimes.json";

    private ArrayList<Crime> crimes;
    private CriminalIntentJSONSerializer serializer;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;


    public void addCrime(Crime c){
        crimes.add(c);
    }
    public void deleteCrime(Crime c){crimes.remove(c);}
    //单例
    public CrimeLab(Context appContext){
        mAppContext = appContext;
        serializer =  new CriminalIntentJSONSerializer(mAppContext,FILENAME);
        try{
            crimes = serializer.loadCrimes();
            Toast.makeText(mAppContext,"Load Successfully",Toast.LENGTH_SHORT)
                    .show();
        }catch (Exception e){
            crimes = new ArrayList<Crime>();
         //   Log.e(TAG,"Sorry");
            Toast.makeText(mAppContext,"Load Failure",Toast.LENGTH_SHORT)
                    .show();
        }


    }
    public static CrimeLab getsCrimeLab(Context c){
        if(sCrimeLab == null)
        {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return crimes;
    }
    public Crime getCrime(UUID id)
    {
        for (Crime c : crimes){
            if(c.getmID().equals(id)){
                return c;
            }
        }
        return null;
    }
    public boolean saveCrimes(){
        try{
            serializer.saveCrimes(crimes);
         //   Log.d(TAG,"OK");
            Toast.makeText(mAppContext,"Success",Toast.LENGTH_SHORT)
                    .show();
            return true;
        }catch (Exception e){
           // Log.e(TAG,"Sorry");
            Toast.makeText(mAppContext,"Failure",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
    }
}
