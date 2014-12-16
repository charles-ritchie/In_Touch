package com.signify.intouch.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by critchie on 15/12/2014.
 */
public class ResponseData {

    private Prefs mPrefs;
    private List<String> mResponseList;

    public ResponseData(Context context) {
        mPrefs = Prefs.getInstance(context);
        mResponseList = new ArrayList<String>();

        String resp = null;
        int x = 0;
        do {
            resp = mPrefs.getString("response"+Integer.toString(x));
            if(resp != null){
                mResponseList.add(resp);
            }
            ++x;
        } while (resp != null);
    }

    public List<String> getResponseList() {
        return mResponseList;
    }

    public void setResponseList(List<String> responseList) {
        mResponseList = responseList;
    }

    public ResponseData setString(String toadd){
        mResponseList.add(toadd);
        return this;
    }

    public void commit(){
        for (int x = 0; x < mResponseList.size(); x++){
            mPrefs.saveString("response"+Integer.toString(x), mResponseList.get(x));
            Log.i("Cheese","response"+Integer.toString(x)+" -> "+ mResponseList.get(x));
        }
    }

    public void commit(Boolean del){
        for (int x = 0; x < mResponseList.size()+1; x++){
            mPrefs.clearString("response"+Integer.toString(x));
            Log.i("Cheese","removing"+Integer.toString(x)+" -> ");
        }
        for (int x = 0; x < mResponseList.size(); x++){
            mPrefs.saveString("response"+Integer.toString(x), mResponseList.get(x));
            Log.i("Cheese","response"+Integer.toString(x)+" -> "+ mResponseList.get(x));
        }
    }

    public ResponseData removeIndex(int ind){
        mResponseList.remove(ind);
        return this;
    }
}
