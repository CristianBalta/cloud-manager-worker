package com.cristianbalta.cloudmanagerworker.controller;

import com.cristianbalta.cloudmanagerworker.dto.UserDto;
import com.cristianbalta.cloudmanagerworker.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerworker.service.LoginAndRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginAndRegistrationController {

    private final LoginAndRegistrationService loginAndRegistrationService;
    private final JwtUtil jwtUtil;

    public LoginAndRegistrationController(JwtUtil jwtUtil, LoginAndRegistrationService loginAndRegistrationService) {
        this.loginAndRegistrationService = loginAndRegistrationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        if (loginAndRegistrationService.checkIfUserExists(userDto.getUserEmail(), userDto.getUserPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("bearerToken", jwtUtil.generateToken(userDto.getUserEmail())));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
