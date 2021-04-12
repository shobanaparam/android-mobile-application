package com.example.it17039000.savealife;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class AddDoner extends AppCompatActivity {
    Button addbtn;
    Button viewbtn;
    Button delbtn;
    Button updatebtn;
    Button sear;
    //Button next;
    EditText editTextsear;
    private BarChart mChart;
    DatabaseHelper db;


    Spinner spinnernames;
    Spinner spinnerunits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doner);

        db = new DatabaseHelper(this);


            spinnernames = (Spinner) findViewById(R.id.spinner);
            spinnerunits = (Spinner) findViewById(R.id.spinner2);
            addbtn = (Button)findViewById(R.id.savebtn);
            viewbtn = (Button)findViewById(R.id.txtviewbtn);
            delbtn = (Button)findViewById(R.id.button3);
            updatebtn = (Button)findViewById(R.id.button);
//            sear = (Button)findViewById(R.id.button2);
//            editTextsear=(EditText) findViewById(R.id.searchh);
            //next=(Button)findViewById(R.id.button4);

        addData();
        viewAll();
        deleteData();
        updateData();
        //onClick();


        mChart = (BarChart) findViewById(R.id.bargraph);
        mChart.getDescription().setEnabled(false);

        setData(8);



        }


    private void setData(int count) {
        ArrayList<BarEntry> yVals = new ArrayList<>();
        ArrayList<BarEntry> xVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * 20);

            yVals.add(new BarEntry(i, (int) value));
           // xVals.add(new BarEntry(i,(int)value1));

        }

        BarDataSet set = new BarDataSet(yVals, "Data Set");
        //set.setColor(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);


        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);



    }



    public  void addData(){
        addbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { if (spinnernames.getSelectedItem().toString().trim().equals("Select")) {
                        Toast.makeText(AddDoner.this, "Select Blood Group and Save", Toast.LENGTH_SHORT).show();
                    }
                        if (spinnerunits.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(AddDoner.this, "Select Blood Units and Save", Toast.LENGTH_SHORT).show();
                        }else{
                        boolean isInserted =    db.insertData(spinnernames.getSelectedItem().toString(),
                                spinnerunits.getSelectedItem().toString());


                        if (isInserted==true)
                            Toast.makeText(AddDoner.this,"Details Are Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddDoner.this,"Details Are Not Inserted",Toast.LENGTH_LONG).show();
                    }}
                }
        );



    }
    public void viewAll(){

        viewbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =  db.getAllData();
                        if (res.getCount()==0){
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Blood Group :"+res.getString(1)+"\n");
                            buffer.append("Units :"+res.getString(2)+"\n\n");


                        }

                        showMessage("Available Blood Details",buffer.toString());
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
    public void deleteData(){
        delbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (spinnernames.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(AddDoner.this, "Select Blood Group and Delete", Toast.LENGTH_SHORT).show();
                        }
                       else{
                        Integer deleterows = db.DeleteData(spinnernames.getSelectedItem().toString());
                        if (deleterows > 0)
                            Toast.makeText(AddDoner.this,"Details Are Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddDoner.this,"Details Are Not Deleted",Toast.LENGTH_LONG).show();
                    }
                }}
        );

    }
    public void updateData(){

        updatebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (spinnernames.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(AddDoner.this, "Select Blood Group and Update", Toast.LENGTH_SHORT).show();
                        }
                        if (spinnerunits.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(AddDoner.this, "Select Blood Units and Update", Toast.LENGTH_SHORT).show();
                        }else{

                        boolean isUpdate = db.updateData(spinnernames.getSelectedItem().toString(),
                                spinnerunits.getSelectedItem().toString());

                        if (isUpdate==true)

                            Toast.makeText(AddDoner.this,"Details Are Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddDoner.this,"Details Are Not Updated",Toast.LENGTH_LONG).show();

                    }}
                }
        );
    }}
   /* public void search()
    {
        sear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.search(editTextsear.getText().toString());

                        if (res.getCount()==0){
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            spinnernames.set(res.getString(1));
                            editTextrono.setText(res.getString(2));


                        }


                    }
                }
        );
    }



        }


        /*Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddDoner.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddDoner.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.units));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        mChart = (BarChart) findViewById(R.id.bargraph);
        mChart.getDescription().setEnabled(false);

        setData(8);
    }

    private void setData(int count) {
        ArrayList<BarEntry> yVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * 20);
            yVals.add(new BarEntry(i, (int) value));

        }

        BarDataSet set = new BarDataSet(yVals, "Data Set");
        //set.setColor(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);

        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);


    }

    private void addBlood() {
        String blood = spinnernames.getSelectedItem().toString();
        String units = spinnerunits.getSelectedItem().toString();

        String sal = "INSERT INTO blood(blood_name,unit)" +
                "VALUES (?,?)";


    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.savebtn:

                addBlood();

                break;
            case R.id.txtviewbtn:
                break;

        }
    }*/

