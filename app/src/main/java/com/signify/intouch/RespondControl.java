package com.signify.intouch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.signify.intouch.data.ResponseData;


public class RespondControl extends ActionBarActivity {

    Spinner responseSpinner;
    ResponseData mResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond_control);

        propagateSpinner();


    }

    private void setButtons(){

    }

    private void propagateSpinner(){
        responseSpinner = (Spinner)findViewById(R.id.response_spinner);
        mResponseData = new ResponseData(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mResponseData.getResponseList());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        responseSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        propagateSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_respond_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
