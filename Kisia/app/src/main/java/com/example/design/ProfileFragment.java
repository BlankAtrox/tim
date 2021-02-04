package com.example.design;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.design.Interfaces.TelemedServ;
import com.example.design.s.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private TextView a1, a2, a3, a4, a5, a6;
    private TelemedServ telemedServ;
    public String encoded, token;

    public ImageView ph;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        a1 = (TextView) rootView.findViewById(R.id.textView26);
        a2 = (TextView) rootView.findViewById(R.id.textView45);
        a3 = (TextView) rootView.findViewById(R.id.textView47);
        a4 = (TextView) rootView.findViewById(R.id.textView49);
        a5 = (TextView) rootView.findViewById(R.id.textView56);
        ph = (ImageView) rootView.findViewById(R.id.imageView2);

        Home home = (Home) getActivity();
        token = home.getMyData();

        extragere();

       // return inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    private void extragere()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://81.180.72.17/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        telemedServ = retrofit.create(TelemedServ.class);

        Call<Users> call  = telemedServ.getProfile(token);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(!response.isSuccessful())
                {
                    return;
                }

                Users post = response.body();

                a1.setText("FullName: " + post.getFullname());
                a2.setText("Birthday: " + post.getBirthday());
                a3.setText("Email: " + post.getEmail());
                a4.setText("Phone: " + post.getPhone());
                a5.setText("Address: " + post.getAdress());
                encoded = post.getBase64photo();
                byte[] data = Base64.decode(encoded, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                ph.setImageBitmap(bitmap);

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }



}