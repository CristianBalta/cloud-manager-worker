package com.cristianbalta.cloudmanagerworker.controller;

import com.cristianbalta.cloudmanagerworker.dto.PairDto;
import com.cristianbalta.cloudmanagerworker.dto.SystemInformationDto;
import com.cristianbalta.cloudmanagerworker.service.CloudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/cloud")
public class CloudController {

    private final CloudService cloudService;

    public CloudController(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    @GetMapping("/{command}")
    public ResponseEntity<String> runCommand(@PathVariable String command) throws IOException {
        PairDto<String, Boolean> pairDto = cloudService.runCommand(command);
        if (!pairDto.getB()) {
            return new ResponseEntity<>(pairDto.getA(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pairDto.getA(), HttpStatus.OK);
    }

    @GetMapping("/system-information")
    public ResponseEntity<SystemInformationDto> getSystemInformation() {
        return ResponseEntity.ok(cloudService.getSystemInformation());
    }
}
