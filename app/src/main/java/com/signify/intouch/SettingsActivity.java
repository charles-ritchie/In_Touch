package com.signify.intouch;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class SettingsActivity extends ActionBarActivity {

    //Constants
    private static final int REQUEST_CONTACTPICKER = 1001;

    //UI views
    Button editResponse;
    Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Set click listeners
        editResponse = (Button)findViewById(R.id.button_edit_response);
        editResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditResponse();
            }
        });

        addContact = (Button)findViewById(R.id.button_add_contact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CONTACTPICKER){
            if(resultCode == RESULT_OK){
                contactPickedOk(data);
            } else{

            }
        }
    }

    public void contactPickedOk(Intent data){
        Uri contactUri = data.getData();
        String contactID = contactUri.getLastPathSegment();
        Log.i("contact",contactID);
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone._ID + "=?",
                new String[]{contactID},null
        );
        Boolean exists = cursor.moveToFirst();
        int phoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        String phoneNumber = "";
        while(exists){
            phoneNumber = cursor.getString(phoneNumberColumnIndex);
            exists = cursor.moveToNext();
        }
        Log.i("number", phoneNumber);
    }

    //Click handler functions
    private void launchEditResponse(){
        Intent intent = new Intent(this, EditCannedActivity.class);
        startActivity(intent);
    }

    private void addContact(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACTPICKER);
    }

    //Fill UI with data
    private void propagateUI(){

    }

}
