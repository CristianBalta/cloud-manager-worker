package com.cristianbalta.cloudmanagerworker.security.model;

public class ErrorResponse {

    private String status;
    private String message;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(String status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
