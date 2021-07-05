package com.cristianbalta.cloudmanagerworker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Value("${worker.username}")
    private String username;

    @Value("${worker.password}")
    private String password;

    public boolean checkIfUserExists(String userEmail, String userPassword) {
        return userEmail.equals(username) && userPassword.equals(password);
    }
}
