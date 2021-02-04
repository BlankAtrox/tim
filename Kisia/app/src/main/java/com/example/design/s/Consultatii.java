package com.example.design.s;

import com.google.gson.annotations.SerializedName;

public class Consultatii {

    @SerializedName("ConsId")
    private int consid;
    @SerializedName("Name")
    private String name;
    @SerializedName("Disease")
    private String diasease;
    @SerializedName("Address")
    private String address;
    @SerializedName("Description")
    private String description;
    @SerializedName("DocId")
    private int docid;
    @SerializedName("IsConfirmed")
    private String idconfirmed;


    public int getConsid() {
        return docid;
    }
    public String getName() {
        return name;
    }
    public String getDiasease() {
        return diasease;
    }
    public String getDescription() {
        return description;
    }
    public String getAdress() {
        return address;
    }
    public int getDocId() {
        return docid;
    }
    public String getIdconfirmed() {
        return idconfirmed;
    }

}
