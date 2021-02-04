package com.example.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.design.Interfaces.TelemedServ;
import com.example.design.s.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {

    private TextView a1, a2;
    Button buttonI,buttonII;
    Intent intent1,intent2;

    String email;
    String password;
    String status = "ERROR";
    String token;

    private TelemedServ telemedServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();


        buttonII = findViewById(R.id.button7);
        intent2 = new Intent(this, HomeActivity.class);

        a1 = findViewById(R.id.textView2);
        a2 = findViewById(R.id.textView3);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://81.180.72.17/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        telemedServ = retrofit.create(TelemedServ.class);

        buttonII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent2);
            }
        });
        intent1 = new Intent(this, Home.class);

        buttonI = findViewById(R.id.button3);
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = a1.getText().toString();
                password = a2.getText().toString();

                inregistrare();
            }
        });
    }

    private void inregistrare()
    {
        Call<Users> call = telemedServ.userAuth(email,password);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(!response.isSuccessful())
                {
                    return;
                }

                Users postResponse = response.body();

                status = postResponse.getStatus();
                token = postResponse.getMessage();

                if(status.equals("SUCCESS")) {
                    intent1.putExtra("hello",token);
                    startActivity(intent1);
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

    }
}