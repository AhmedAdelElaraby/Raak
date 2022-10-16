package com.teraninja.raak.Model;

import java.util.ArrayList;

public class DataNewSebiscribe {
    public int status;
    public boolean error;
    public String errors;
    public String message;
    public ArrayList<Datasbiscribe> data;
    public int server_status;

    public DataNewSebiscribe(int status, String errors, String message) {
        this.status = status;
        this.errors = errors;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Datasbiscribe> getData() {
        return data;
    }

    public void setData(ArrayList<Datasbiscribe> data) {
        this.data = data;
    }

    public int getServer_status() {
        return server_status;
    }

    public void setServer_status(int server_status) {
        this.server_status = server_status;
    }
}
