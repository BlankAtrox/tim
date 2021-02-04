package com.example.design;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.design.Interfaces.Doctorul;
import com.example.design.Interfaces.TelemedServ;
import com.example.design.s.Consultatii;
import com.example.design.s.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements  View.OnClickListener {

    private TelemedServ telemedServ;
    public String token;

    Intent intent1, intent2;;

    private TextView a1, a2, a3, a4;

    String consid1,name1,disease1,address1,description1,docid1,isconfirmed1;
    int idiul;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        intent1 = new Intent(getActivity(), Doctorul.class);

        Home home = (Home) getActivity();
        token = home.getMyData();

        a1 = (TextView) rootView.findViewById(R.id.textView11);
        a2 = (TextView) rootView.findViewById(R.id.textView14);
        a3 = (TextView) rootView.findViewById(R.id.textView17);
        a4 = (TextView) rootView.findViewById(R.id.textView20);

        final Button buttonI = (Button) rootView.findViewById(R.id.button22);

        buttonI.setOnClickListener(this);

        return rootView;
    }


    private void extragere()
    {
        String name, disease,adres,description;

        name = a1.getText().toString();
        disease = a2.getText().toString();
        adres = a3.getText().toString();
        description = a4.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://81.180.72.17/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        telemedServ = retrofit.create(TelemedServ.class);

        Call<Consultatii> call  = telemedServ.addCons(token,name,disease,adres,description);

        call.enqueue(new Callback<Consultatii>() {
            @Override
            public void onResponse(Call<Consultatii> call, Response<Consultatii> response) {

                if(!response.isSuccessful())
                {
                    return;
                }

                Consultatii post = response.body();

                consid1 = "ConsId: " + post.getConsid();
                name1="Name: " + post.getName();
                disease1 = "Disease: " + post.getDiasease();
                address1 ="Address: " + post.getAdress();
                description1 ="Description: " + post.getDescription();
                docid1 ="Address: " + post.getDocId();
                isconfirmed1 ="IsConfirmed " + post.getIdconfirmed();
                idiul = post.getDocId();

                a1.setText("");
                a2.setText("");
                a3.setText("");
                a4.setText("");

            }

            @Override
            public void onFailure(Call<Consultatii> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button22:
                extragere();

                break;
        }
    }
}
