package com.teraninja.raak.Model;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public class SendDataComiop {
    String maintenance_service_id;
    String message;
    ArrayList<MultipartBody.Part> images;

    public String getMaintenance_service_id() {
        return maintenance_service_id;
    }

    public void setMaintenance_service_id(String maintenance_service_id) {
        this.maintenance_service_id = maintenance_service_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<MultipartBody.Part> getImages() {
        return images;
    }

    public void setImages(ArrayList<MultipartBody.Part> images) {
        this.images = images;
    }
}
