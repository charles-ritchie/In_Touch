package com.signify.intouch;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class SettingsActivity extends ActionBarActivity {

    private static final int REQUEST_CONTACTPICKER = 1;
    Button editResponse;
    Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editResponse = (Button)findViewById(R.id.button_edit_response);
        editResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEditCannedActivity();
            }
        });

        addContact = (Button)findViewById(R.id.button_contact_add);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    private void gotoEditCannedActivity(){
        Intent intent = new Intent(this, EditCannedActivity.class);
        startActivity(intent);
    }

    private void addContact(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACTPICKER);
    }

    private void propagateData(){

    }

}
