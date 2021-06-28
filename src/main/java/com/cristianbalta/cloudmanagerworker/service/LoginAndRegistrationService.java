package com.cristianbalta.cloudmanagerworker.service;

import org.springframework.stereotype.Service;

@Service
public class LoginAndRegistrationService {

//    public LoginAndRegistrationService(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }

    public boolean checkIfUserExists(String userEmail, String userPassword) {
//        User user = usersRepository.findByUserEmail(userEmail);
//        if (user != null) {
//            return user.getUserPassword().equals(userPassword);
//        } else return false;
        String user = "email-value0";
        String password = "password_value";
        return userEmail.equals(user) && userPassword.equals(password);
    }
}
