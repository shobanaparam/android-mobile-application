package com.example.it17039000.savealife;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String DATABASE_NAME = "myDatabase1";

    MyDatabaseManager mDatabase;

    EditText editName, editHospital, editPhone,editBloodGroup,editUnit,editPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new MyDatabaseManager(this);
        //createTable();

        editName = (EditText)findViewById(R.id.editName);
        editHospital = (EditText)findViewById(R.id.editHospital);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editBloodGroup =(EditText) findViewById(R.id.editBloodGroup);
        editUnit =(EditText) findViewById(R.id.editUnit);
        editPlace =(EditText) findViewById(R.id.editPlace);

        findViewById(R.id.buttonPost).setOnClickListener(this);
        findViewById(R.id.btnViewRequests).setOnClickListener(this);
    }

    /*private void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Blood1(\n" +
                "\t\t id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\t name v NOT NULL,\n" +
                "\t\t blood_grp varchar(10) NOT NULL,\n" +
                "\t\t unit integer NOT NULL,\n" +
                "\t\t hospital varchar(100) NOT NULL,\n" +
                "\t\t phone integer NOT NULL,\n" +
                "\t\t place varchar(100) NOT NULL)";

        mDatabase.execSQL(sql);
    }*/

    private void addBloodPost(){
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

        if(unit.isEmpty()){
            editUnit.setError("Enter the unit");
            editUnit.requestFocus();
            return;
        }

        if(place.isEmpty()){
            editPlace.setError("Enter the place");
            editPlace.requestFocus();
            return;
        }

        if(bloodGroup.isEmpty()){
            editBloodGroup.setError("Enter the blood group");
            editBloodGroup.requestFocus();
            return;
        }

        //Calendar cal = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        //String joiningDate = sdf.format(cal.getTime());

        if(mDatabase.addBloodRequests(name,bloodGroup,unit,hospital,phone,place))
            Toast.makeText(this, "Blood Request Posted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Blood Request not added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPost:
                addBloodPost();
                break;

            case R.id.btnViewRequests:
                startActivity(new Intent(this, ViewRequests.class));
                break;
        }
    }
}
