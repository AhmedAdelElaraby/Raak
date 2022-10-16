package com.teraninja.raak.Model;

import android.hardware.Camera;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Detilse {
    public int id;
    public Object user_id;
    public int area_id;
    public Areaa area;
    public int building_type_id;
    public BuildingType building_type;
    public ArrayList<Object> maintenance_contract;
    public String residential_unit_number;
    public String address;
    @SerializedName("long")
    public String mylong;
    public String lat;
    public String image;
    public int bath_rooms;
    public int bed_rooms;
    public double size;
    public int swimming_pool;
    public int car_parking;
    public Object garden_service;
    public int price;
    public ArrayList<Imagepost> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUser_id() {
        return user_id;
    }

    public void setUser_id(Object user_id) {
        this.user_id = user_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public Areaa getArea() {
        return area;
    }

    public void setArea(Areaa area) {
        this.area = area;
    }

    public int getBuilding_type_id() {
        return building_type_id;
    }

    public void setBuilding_type_id(int building_type_id) {
        this.building_type_id = building_type_id;
    }

    public BuildingType getBuilding_type() {
        return building_type;
    }

    public void setBuilding_type(BuildingType building_type) {
        this.building_type = building_type;
    }

    public ArrayList<Object> getMaintenance_contract() {
        return maintenance_contract;
    }

    public void setMaintenance_contract(ArrayList<Object> maintenance_contract) {
        this.maintenance_contract = maintenance_contract;
    }

    public String getResidential_unit_number() {
        return residential_unit_number;
    }

    public void setResidential_unit_number(String residential_unit_number) {
        this.residential_unit_number = residential_unit_number;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBath_rooms() {
        return bath_rooms;
    }

    public void setBath_rooms(int bath_rooms) {
        this.bath_rooms = bath_rooms;
    }

    public int getBed_rooms() {
        return bed_rooms;
    }

    public void setBed_rooms(int bed_rooms) {
        this.bed_rooms = bed_rooms;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getSwimming_pool() {
        return swimming_pool;
    }

    public void setSwimming_pool(int swimming_pool) {
        this.swimming_pool = swimming_pool;
    }

    public int getCar_parking() {
        return car_parking;
    }

    public void setCar_parking(int car_parking) {
        this.car_parking = car_parking;
    }

    public Object getGarden_service() {
        return garden_service;
    }

    public void setGarden_service(Object garden_service) {
        this.garden_service = garden_service;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Imagepost> getImages() {
        return images;
    }

    public void setImages(ArrayList<Imagepost> images) {
        this.images = images;
    }
}
