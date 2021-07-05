package com.cristianbalta.cloudmanagerworker.controller;

import com.cristianbalta.cloudmanagerworker.dto.UserDto;
import com.cristianbalta.cloudmanagerworker.dto.WorkerBearerDto;
import com.cristianbalta.cloudmanagerworker.security.jwt.JwtUtil;
import com.cristianbalta.cloudmanagerworker.service.LoginService;
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
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public LoginController(JwtUtil jwtUtil, LoginService loginService) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        if (loginService.checkIfUserExists(userDto.getUserEmail(), userDto.getUserPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("bearerToken", jwtUtil.generateToken(userDto.getUserEmail())));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/app")
    public ResponseEntity<WorkerBearerDto> applicationLogin(@RequestBody UserDto userDto) {
        if (loginService.checkIfUserExists(userDto.getUserEmail(), userDto.getUserPassword())) {
            WorkerBearerDto workerBearerDto = new WorkerBearerDto();
            workerBearerDto.setWorkerBearerDto(Collections.singletonMap("bearerToken", jwtUtil.generateToken(userDto.getUserEmail())));
            return ResponseEntity.ok(workerBearerDto);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
