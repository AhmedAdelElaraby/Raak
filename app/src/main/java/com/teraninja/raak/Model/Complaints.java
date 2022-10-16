package com.teraninja.raak.Model;

import android.media.Image;

import java.util.ArrayList;

public class Complaints {
    public int id;
    public int user_id;
    public int maintenance_service_id;
    public String message;
    public ArrayList<Imagepost> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMaintenance_service_id() {
        return maintenance_service_id;
    }

    public void setMaintenance_service_id(int maintenance_service_id) {
        this.maintenance_service_id = maintenance_service_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Imagepost> getImages() {
        return images;
    }

    public void setImages(ArrayList<Imagepost> images) {
        this.images = images;
    }
}
