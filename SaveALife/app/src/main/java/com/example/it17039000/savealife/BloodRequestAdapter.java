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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class BloodRequestAdapter extends ArrayAdapter<BloodRequests> {

    Context mCtx;
    int layoutRes;
    List<BloodRequests> bloodRequestsList;
    MyDatabaseManager mDatabase;

    public BloodRequestAdapter(Context mCtx, int layoutRes, List<BloodRequests> bloodRequestsList, MyDatabaseManager mDatabase){
        super(mCtx, layoutRes, bloodRequestsList);

        this.mCtx = mCtx;
        this.layoutRes = layoutRes;
        this.bloodRequestsList = bloodRequestsList;
        this.mDatabase = mDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(layoutRes,null);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtBloodGroup = view.findViewById(R.id.txtBloodGroup);
        TextView txtUnit = view.findViewById(R.id.txtUnit);
        TextView txtHospital = view.findViewById(R.id.txtHospital);
        TextView txtPhone = view.findViewById(R.id.txtPhone);
        TextView txtPlace = view.findViewById(R.id.txtPlace);

        final BloodRequests bloodRequests = bloodRequestsList.get(position);

        txtName.setText(bloodRequests.getName());
        txtBloodGroup.setText(bloodRequests.getBloodGroup());
        txtUnit.setText(String.valueOf(bloodRequests.getUnit()));
        txtHospital.setText(bloodRequests.getHospital());
        txtPhone.setText(String.valueOf(bloodRequests.getPhone()));
        txtPlace.setText(bloodRequests.getPlace());

        //Button btnEdit = view.findViewById(R.id.btnEdit);
        //Button buttonDeleteBloodReq = view.findViewById(R.id.buttonDeleteBloodReq);

        view.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBloodRequest(bloodRequests);
            }
        });

        view.findViewById(R.id.buttonDeleteBloodReq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBloodRequest(bloodRequests);
            }
        });

        return view;
    }

    private void updateBloodRequest(final BloodRequests bloodRequests){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_req, null);
        builder.setView(view);

        final EditText editName = view.findViewById(R.id.editName);
        final EditText editBloodGroup = view.findViewById(R.id.editBloodGroup);
        final EditText editUnit = view.findViewById(R.id.editUnit);
        final EditText editHospital = view.findViewById(R.id.editHospital);
        final EditText editPhone = view.findViewById(R.id.editPhone);
        final EditText editPlace = view.findViewById(R.id.editPlace);

        editName.setText(bloodRequests.getName());
        editBloodGroup.setText(bloodRequests.getBloodGroup());
        editUnit.setText(String.valueOf(bloodRequests.getUnit()));
        editHospital.setText(bloodRequests.getHospital());
        editPhone.setText(String.valueOf(bloodRequests.getPhone()));
        editPlace.setText(bloodRequests.getPlace());

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        view.findViewById(R.id.updateRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString().trim();
                String bloodGroup = editBloodGroup.getText().toString().trim();
                String unit = editUnit.getText().toString().trim();
                String hospital = editHospital.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String place = editPlace.getText().toString().trim();

                if(name.isEmpty()){
                    editName.setError("Fill the name");
                    editName.requestFocus();
                    return;
                }

                if(hospital.isEmpty()){
                    editHospital.setError("Enter the Hospital name");
                    editHospital.requestFocus();
                    return;
                }

                if(phone.isEmpty()){
                    editPhone.setError("Enter the number");
                    editPhone.requestFocus();
                    return;
                }


                //String sql = "UPDATE Blood1 SET name=?, blood_grp =?, unit=?, hospital=?, phone=?, place=? WHERE id = ?";
                if(mDatabase.updateBloodrequests(bloodRequests.getId(),name, bloodGroup, unit, hospital, phone,place)) {
                    Toast.makeText(mCtx, "Blood Post Updated", Toast.LENGTH_SHORT).show();
                    loadBloodRequestsFromDatabaseAgain();
                }else{
                    Toast.makeText(mCtx, "Blood Post not Updated", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();

            }
        });
    }

    private void loadBloodRequestsFromDatabaseAgain() {
        //String sql = "SELECT * FROM Blood1";

        Cursor cursor = mDatabase.getBloodRequests();

        if (cursor.moveToFirst()) {
            bloodRequestsList.clear();
        do {
            bloodRequestsList.add(new BloodRequests(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));

        } while (cursor.moveToNext());
    }
        cursor.close();
        notifyDataSetChanged();
    }

    private void deleteBloodRequest(final BloodRequests bloodRequests){
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //String sql = "DELETE FROM Blood1 WHERE id =?";
                if(mDatabase.deleteBloodRequests(bloodRequests.getId()))
                loadBloodRequestsFromDatabaseAgain();
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
