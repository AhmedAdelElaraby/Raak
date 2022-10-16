package com.teraninja.raak.Model;

public class Order {
    public int id;
    public int building_id;
    public int contract_type_id;
    public String start_date;
    public String end_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public int getContract_type_id() {
        return contract_type_id;
    }

    public void setContract_type_id(int contract_type_id) {
        this.contract_type_id = contract_type_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
