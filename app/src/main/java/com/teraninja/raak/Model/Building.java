package com.teraninja.raak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Building {
    public int id;
    public int user_id;
    public String residential_unit_number;
    public ArrayList<MaintenanceContract> maintenance_contract;
    public String address;
    @SerializedName("long")
    public String mylong;
    public String lat;

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

    public String getResidential_unit_number() {
        return residential_unit_number;
    }

    public void setResidential_unit_number(String residential_unit_number) {
        this.residential_unit_number = residential_unit_number;
    }

    public ArrayList<MaintenanceContract> getMaintenance_contract() {
        return maintenance_contract;
    }

    public void setMaintenance_contract(ArrayList<MaintenanceContract> maintenance_contract) {
        this.maintenance_contract = maintenance_contract;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMylong() {
        return mylong;
    }

    public void setMylong(String mylong) {
        this.mylong = mylong;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
