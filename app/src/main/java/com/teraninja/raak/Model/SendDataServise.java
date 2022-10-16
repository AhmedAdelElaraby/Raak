package com.teraninja.raak.Model;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public class SendDataServise {
    String building_id;
    String user_id;
    String damage_location_id;
    String damage_type_id;
    String message;
    String day_from;
    String day_to;
   ArrayList<MultipartBody.Part> images;

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDamage_location_id() {
        return damage_location_id;
    }

    public void setDamage_location_id(String damage_location_id) {
        this.damage_location_id = damage_location_id;
    }

    public String getDamage_type_id() {
        return damage_type_id;
    }

    public void setDamage_type_id(String damage_type_id) {
        this.damage_type_id = damage_type_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDay_from() {
        return day_from;
    }

    public void setDay_from(String day_from) {
        this.day_from = day_from;
    }

    public String getDay_to() {
        return day_to;
    }

    public void setDay_to(String day_to) {
        this.day_to = day_to;
    }

    public ArrayList<MultipartBody.Part> getImages() {
        return images;
    }

    public void setImages(ArrayList<MultipartBody.Part> images) {
        this.images = images;
    }
}
