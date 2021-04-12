package com.example.it17039000.savealife;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewRequests extends AppCompatActivity {

    MyDatabaseManager mDatabase;

    List<BloodRequests> bloodRequestsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);

        mDatabase = new MyDatabaseManager(this);
        bloodRequestsList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listViewBloodRequests);

        loadBloodRequestsFromDatabase();
    }

    private void loadBloodRequestsFromDatabase(){

        Cursor cursor = mDatabase.getBloodRequests();

        if(cursor.moveToFirst()){
            do{
                bloodRequestsList.add(new BloodRequests(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));

            }while(cursor.moveToNext());

            BloodRequestAdapter adapter = new BloodRequestAdapter(this, R.layout.list_lauout_blood_requests, bloodRequestsList, mDatabase);
            listView.setAdapter(adapter);

        }

    }
}
