package com.example.design.Interfaces;

import com.example.design.s.Consultatii;
import com.example.design.s.Doctori;
import com.example.design.s.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TelemedServ {


    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/api/Register/UserReg")
    Call<Users> userReg(
            @Field("FullName") String fullname,
            @Field("Birthday") String birthday,
            @Field("Email") String email,
            @Field("Phone") String phone,
            @Field("Address") String adress,
            @Field("Username") String username,
            @Field("Password") String password,
            @Field("Base64Photo") String base64photo
    );

    @Headers({"Content-type: application/json"})
    //@Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/api/Register/UserReg")
    Call<Users> userReg(@Body Users post);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/api/Login/UserAuth")
    Call<Users> userAuth(
            @Field("Email") String email,
            @Field("Password") String password
    );

        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("/api/Doctor/AddConsultation")
        Call<Consultatii> addCons(
                @Header("token") String token,
                @Field("Name") String name,
                @Field("Disease") String disease,
                @Field("Address") String address,
                @Field("Description") String description
        );

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("/api/Profile/GetProfile")
    Call<Users> getProfile(
            @Header("token") String token
    );

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("/api/Doctor/GetDoctorList")
    Call<List<Doctori>> getList(
            @Header("token") String token
    );
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("/api/Doctor/GetDoctor/{id}")
    Call<Doctori> getDoc(
            @Path("id") int id,
            @Header("token") String token
    );


}

