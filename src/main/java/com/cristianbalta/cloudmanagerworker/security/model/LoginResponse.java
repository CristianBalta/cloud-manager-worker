package com.cristianbalta.cloudmanagerworker.security.model;

import java.util.Date;

public class LoginResponse {

    private String jwt;

    private String username;

    private Date expirationDate;

    public LoginResponse() {
    }

    public LoginResponse(String jwt, String username, Date expirationDate) {
        this.jwt = jwt;
        this.username = username;
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
