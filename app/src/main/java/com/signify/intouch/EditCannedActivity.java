package com.signify.intouch;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.signify.intouch.data.ResponseData;

public class EditCannedActivity extends ListActivity {

    ResponseData mResponseData;
    TextView responseField;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_canned);

        mResponseData = new ResponseData(this);

        //Set Adapter for list;
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mResponseData.getResponseList());
        setListAdapter(adapter);

        //Click listeners
        ImageButton addButton = (ImageButton)findViewById(R.id.button_response_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResponse();
                adapter.notifyDataSetChanged();
            }
        });

        responseField = (TextView)findViewById(R.id.textview_add_response);
    }

    public void saveResponse(){
        if(responseField.getText().length() > 3) {
            String resp = responseField.getText().toString();
            mResponseData.setString(resp).commit();
            responseField.setText("");
        } else {
            Toast toast = Toast.makeText(this, "Message length is too short.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void showConfirmDelete(int toDelete){
        final int position = toDelete;
        new AlertDialog.Builder(this)
                .setTitle("Delete Message")
                .setMessage("Do you really want to delete this canned message?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mResponseData.removeIndex(position).commit(true);
                        adapter.notifyDataSetChanged();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        String selectedItem = (String) getListView().getItemAtPosition(position);
        showConfirmDelete(position);
    }
}
