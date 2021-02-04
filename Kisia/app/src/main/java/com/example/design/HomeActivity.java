package com.example.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button buttonI,buttonII,buttonIII;
    Intent intent1,intent2,intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        intent1 = new Intent(this, login.class);

        buttonI = findViewById(R.id.button6);
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent1);
            }
        });
        buttonII = findViewById(R.id.button3);
        intent2 = new Intent(this, register.class);

        buttonII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent2);
            }
        });

        intent3 = new Intent(this, Home.class);

        buttonIII = findViewById(R.id.button7);
        buttonIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent3);
            }
        });
    }
}