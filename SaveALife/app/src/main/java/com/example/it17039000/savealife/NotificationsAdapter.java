package com.example.it17039000.savealife;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NotificationsAdapter extends ArrayAdapter<NotificationPanel> {
    Context mCtx;
    int layoutRes1;
    List<NotificationPanel> notificationPanelList;
    MyDatabaseManager mDatabase;

    public NotificationsAdapter(Context mCtx, int layoutRes1, List<NotificationPanel> notificationPanelList, MyDatabaseManager mDatabase){
        super(mCtx, layoutRes1, notificationPanelList);

        this.mCtx = mCtx;
        this.layoutRes1 = layoutRes1;
        this.notificationPanelList = notificationPanelList;
        this.mDatabase = mDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(layoutRes1,null);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtBloodGroup = view.findViewById(R.id.txtBloodGroup);
        TextView txtPhone = view.findViewById(R.id.txtPhone);

        final NotificationPanel notificationPanel = notificationPanelList.get(position);

        txtName.setText("Mr." + notificationPanel.getName() + " needs blood");
        txtBloodGroup.setText("His blood group is " + notificationPanel.getBloodGroup());
        txtPhone.setText("Contact " + notificationPanel.getPhone() + " to help");

        //Button btnEdit = view.findViewById(R.id.btnEdit);
        //Button buttonDeleteBloodReq = view.findViewById(R.id.buttonDeleteBloodReq);

        view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNotification(notificationPanel);
            }
        });


        return view;
    }

    private void loadNotificationsFromDatabaseAgain() {
        //String sql = "SELECT * FROM Blood1";

        Cursor cursor = mDatabase.getBloodRequests();

        if (cursor.moveToFirst()) {
            notificationPanelList.clear();
            do {
                notificationPanelList.add(new NotificationPanel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(5)
                ));

            } while (cursor.moveToNext());
        }
        cursor.close();
        notifyDataSetChanged();
    }


    private void deleteNotification(final NotificationPanel notificationPanel){
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //String sql = "DELETE FROM Blood1 WHERE id =?";
                if(mDatabase.deleteBloodRequests(notificationPanel.getId()))
                    loadNotificationsFromDatabaseAgain();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
