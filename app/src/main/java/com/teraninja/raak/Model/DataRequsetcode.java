package com.teraninja.raak.Model;

public class DataRequsetcode {
    public int status;
    public boolean error;
    public String errors;
    public String message;
    public code data;
    public int server_status;

    public DataRequsetcode(int status, String message,String errors) {
        this.status = status;
        this.errors=errors;
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

    public code getData() {
        return data;
    }

    public void setData(code data) {
        this.data = data;
    }

    public int getServer_status() {
        return server_status;
    }

    public void setServer_status(int server_status) {
        this.server_status = server_status;
    }
}
