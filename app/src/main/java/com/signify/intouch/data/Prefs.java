package com.signify.intouch.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by critchie on 15/12/2014.
 */
public class Prefs {
    private static final String PREFS = "com.signify.intouch.prefs";
    private static Prefs instance = null;
    public static SharedPreferences mPref;

    private Prefs(Context context) {
        this.mPref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static Prefs getInstance(Context context){
        if (instance == null){
            instance = new Prefs(context);
        }
        return instance;
    }

    public void saveString(String key, String value){
        this.mPref.edit().putString(key, value).commit();
    }

    public String getString(String key){
        return this.mPref.getString(key, null);
    }

    public void clearString(String toclear){
        this.mPref.edit().remove(toclear).commit();
        Log.i("cheese", "clear key:'" + toclear + "'");
    }

    public void clearAllEntries(){
        this.mPref.edit().clear().commit();
    }
}
