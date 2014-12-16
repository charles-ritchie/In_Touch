package com.signify.intouch;

import android.app.ActionBar;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.signify.intouch.R;
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

        ImageButton addButton = (ImageButton)findViewById(R.id.button_response_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveResponseFromField();
                adapter.notifyDataSetChanged();
            }
        });

        responseField = (TextView)findViewById(R.id.textview_add_response);
    }

    public void saveResponseFromField(){
        String resp = responseField.getText().toString();
        mResponseData.setString(resp).commit();
        responseField.setText("");
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        String selectedItem = (String) getListView().getItemAtPosition(position);

        mResponseData.removeIndex(position).commit(true);
        adapter.notifyDataSetChanged();

    }
}
