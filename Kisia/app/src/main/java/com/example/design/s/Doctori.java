package com.example.design.s;

import com.google.gson.annotations.SerializedName;

public class Doctori {

    @SerializedName("DocId")
    private int docid;
    @SerializedName("FullName")
    private String fullname;
    @SerializedName("Specs")
    private String specs;
    @SerializedName("About")
    private String about;
    @SerializedName("Address")
    private String adress;
    @SerializedName("Stars")
    private String stars;
    @SerializedName("Photo")
    private String photo;


    public int getDocid() {
        return docid;
    }
    public String getFullname() {
        return fullname;
    }
    public String getSpecs() {
        return specs;
    }
    public String getAbout() {
        return about;
    }
    public String getAdress() {
        return adress;
    }
    public String getStars() {
        return stars;
    }
    public String getPhoto() {
        return photo;
    }


}