package com.example.it17039000.savealife;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogInMainActivity extends AppCompatActivity {
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_main);

        myDialog = new Dialog(this);
        Button mButton = (Button)findViewById(R.id.postBloodRequest);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //you can use anything in place of i
                Intent i = new Intent(LogInMainActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        Button mButton1 = (Button)findViewById(R.id.notification);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //you can use anything in place of i
                Intent i = new Intent(LogInMainActivity.this, ViewNotificaionPanel.class);
                startActivity(i);

            }
        });
    }

    public void showPopup(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.learn_more_pop_up);
        txtClose =(TextView) myDialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
