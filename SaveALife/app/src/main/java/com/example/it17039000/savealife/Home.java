
package com.example.it17039000.savealife;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    Button b5;
    ImageView post1;
    ImageView noti1;
    ImageView event1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b5=(Button) findViewById(R.id.bloodbank);
        post1=(ImageView)findViewById(R.id.post);
        noti1=(ImageView)findViewById(R.id.noti);
        event1=(ImageView)findViewById(R.id.event) ;
        onClick();
        onClick1();
        onClick2();
        onClick3();

    
    }
    public  void onClick(){
        b5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Home.this,AddDoner.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public  void onClick1(){
        post1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Home.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    public  void onClick2(){
        noti1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Home.this,ViewNotificaionPanel.class);
                        startActivity(intent);
                    }
                }
        );
    }
    public  void onClick3(){
        event1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Home.this,MainActivity1.class);
                        startActivity(intent);
                    }
                }
        );
    }


}


