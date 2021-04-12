package com.example.it17039000.savealife;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
    SQLiteHelper myDb;
    EditText editTextname, editTextdate, editTextaddress, editTexttime, editTextsearch;
    Button add;
    Button view;
    Button update;
    Button delete;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        myDb = new SQLiteHelper(this);

        editTextname = (EditText)findViewById(R.id.name);
        editTextdate = (EditText)findViewById(R.id.date);
        editTextaddress = (EditText)findViewById(R.id.address);
        editTexttime = (EditText)findViewById(R.id.time);
        editTextsearch = (EditText)findViewById(R.id.search);
        add = (Button)findViewById(R.id.addbtn);
        view = (Button)findViewById(R.id.viewbtn);
        update = (Button)findViewById(R.id.updatebtn);
        delete = (Button)findViewById(R.id.deletebtn);
        search = (Button)findViewById(R.id.searchbtn);
        addData();
        viewAll();
        updateData();
        deleteData();
        search();
    }

    public void deleteData() {
        delete.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Integer deleterows = myDb.DeleteData(editTextname.getText().toString());
                        if (deleterows > 0)
                            Toast.makeText(MainActivity1.this, "Details Are Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity1.this, "Details Are Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }

    public void updateData() {

        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextname.getText().toString(),
                                editTextdate.getText().toString(),
                                editTextaddress.getText().toString(),
                                editTexttime.getText().toString());
                        if (isUpdate == true)

                            Toast.makeText(MainActivity1.this, "Details Are Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity1.this, "Details Are Not Updated", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void addData() {
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(editTextname.getText().toString(),
                                editTextdate.getText().toString(),
                                editTextaddress.getText().toString(),
                                editTexttime.getText().toString());

                        if (isInserted == true)
                            Toast.makeText(MainActivity1.this, "Details Are Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity1.this, "Details Are Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void search() {
        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.search(editTextsearch.getText().toString());

                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            editTextname.setText(res.getString(1));
                            editTextdate.setText(res.getString(2));
                            editTextaddress.setText(res.getString(3));
                            editTexttime.setText(res.getString(4));
                        }


                    }


                }
        );
    }

    public void viewAll() {

        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Event Name :" + res.getString(1) + "\n");
                            buffer.append("Date :" + res.getString(2) + "\n");
                            buffer.append("Address :" + res.getString(3) + "\n");
                            buffer.append("Time : :" + res.getString(4) + "\n");

                        }

                        showMessage("Event Details", buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title, String Messsage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messsage);
        builder.show();


    }
}




