package com.teraninja.raak.Model;

import java.util.ArrayList;

public class DataOrdersServes {
    public int id;
    public Building building;
    public DamageLocation damage_location;
    public DamageType damage_type;
    public ArrayList<Complaints> complaints;
    public String message;
    public String day_from;
    public String day_to;
    public ArrayList<Imagepost> images;
    public String status;
    public String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public DamageLocation getDamage_location() {
        return damage_location;
    }

    public void setDamage_location(DamageLocation damage_location) {
        this.damage_location = damage_location;
    }

    public DamageType getDamage_type() {
        return damage_type;
    }

    public void setDamage_type(DamageType damage_type) {
        this.damage_type = damage_type;
    }

    public ArrayList<Complaints> getComplaints() {
        return complaints;
    }

    public void setComplaints(ArrayList<Complaints> complaints) {
        this.complaints = complaints;
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

    public ArrayList<Imagepost> getImages() {
        return images;
    }

    public void setImages(ArrayList<Imagepost> images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
