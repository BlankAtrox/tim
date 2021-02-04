package com.example.design.Interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.design.R;
import com.example.design.s.Doctori;
import com.example.design.s.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Doctorul extends AppCompatActivity {

    String myString,encoded;
    int id;

    Button buttonII;

    private TextView a1, a2, a3, a4, a5, a6;
    public ImageView ph;

    private TelemedServ telemedServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorul);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://81.180.72.17/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        telemedServ = retrofit.create(TelemedServ.class);

        Bundle arguments = getIntent().getExtras();
        myString = arguments.get("hello1").toString();
        id = getIntent().getIntExtra("hello2",1);

        a1 = findViewById(R.id.textView26doc);
        a2 = findViewById(R.id.textView45doc);
        a3 = findViewById(R.id.textView47doc);
        a4 = findViewById(R.id.textView49doc);
        a5 = findViewById(R.id.textView56doc);
        ph = findViewById(R.id.imageView2doc);
        buttonII = findViewById(R.id.button22doc);


        dupa_id();

        buttonII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }

    private void dupa_id()
    {
        Call<Doctori> call = telemedServ.getDoc(id,myString);

        call.enqueue(new Callback<Doctori>() {
            @Override
            public void onResponse(Call<Doctori> call, Response<Doctori> response) {

                if(!response.isSuccessful())
                {
                    return;
                }

                Doctori postResponse = response.body();


                a1.setText("FullName: " + postResponse.getFullname());
                a2.setText("Specs: " + postResponse.getSpecs());
                a3.setText("Adress: " + postResponse.getAdress());
                a4.setText("About: " + postResponse.getAbout());
                a5.setText("Stars: " + postResponse.getStars());
                encoded = postResponse.getPhoto();
                byte[] data = Base64.decode(encoded, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                ph.setImageBitmap(bitmap);

            }

            @Override
            public void onFailure(Call<Doctori> call, Throwable t) {


            }
        });

    }
}