package com.example.mycontribution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button Contribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Contribute=findViewById(R.id.contibutebtn);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        getSupportActionBar().hide();


        Contribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);

                startActivity(intent);
            }
        });


    }
}