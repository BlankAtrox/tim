package com.example.design.s;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("FullName")
    private String fullname;
    @SerializedName("Birthday")
    private String birthday;
    @SerializedName("Email")
    private String email;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Address")
    private String adress;
    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;
    @SerializedName("Base64Photo")
    private String base64photo;
    @SerializedName("Status")
    private String status;
    @SerializedName("Message")
    private String message;

    public Users(String fullname, String birthday, String email, String phone, String adress, String username, String password, String base64photo) {
        this.fullname = fullname;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.username = username;
        this.password = password;
        this.base64photo = base64photo;
    }

    public String getFullname() {
        return fullname;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAdress() {
        return adress;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getBase64photo() {
        return base64photo;
    }
    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }


}