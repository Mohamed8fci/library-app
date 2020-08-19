package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnSeeAll,btnCurrentlyReading,btnwantToRead,btnAlreadyRead,btnAbout;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initwidgets();
        setOnClickListener();
    }
    private void setOnClickListener(){
        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);
            }
        });
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlreadyReadActivity.class);
                startActivity(intent);
            }
        });
        btnwantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,wantToActivity.class);
                startActivity(intent);
            }
        });
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CurrentlyReadingActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this,AboutActivity.class);
             startActivity(intent);
            }
        });
    }
    private void initwidgets(){
        btnSeeAll=(Button) findViewById(R.id.btnAllbooks);
        btnCurrentlyReading=(Button) findViewById(R.id.btncurent);
        btnAlreadyRead=(Button) findViewById(R.id.btnAlready);
        btnwantToRead=(Button) findViewById(R.id.btnwantto);
        btnAbout=(Button) findViewById(R.id.btnAbout);

    }
}
