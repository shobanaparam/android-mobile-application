package com.example.it17039000.savealife;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewNotificaionPanel extends AppCompatActivity {

    MyDatabaseManager mDatabase;

    List<NotificationPanel> notificationPanelList;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaion_panel);

        mDatabase = new MyDatabaseManager(this);
        notificationPanelList = new ArrayList<>();
        listView1 = (ListView)findViewById(R.id.listViewNotifications);

        loadNotifications();
    }

    private void loadNotifications(){

        Cursor cursor1 = mDatabase.getBloodNotifications();

        if(cursor1.moveToFirst()){
            do{
                notificationPanelList.add(new NotificationPanel(
                        cursor1.getInt(0),
                        cursor1.getString(1),
                        cursor1.getString(2),
                        cursor1.getString(5)
                ));

            }while(cursor1.moveToNext());

            NotificationsAdapter adapter = new NotificationsAdapter(this, R.layout.list_layout_notifications, notificationPanelList, mDatabase);
            listView1.setAdapter(adapter);

        }
    }
}
