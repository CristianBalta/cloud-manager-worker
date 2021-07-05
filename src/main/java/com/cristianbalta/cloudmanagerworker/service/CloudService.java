package com.cristianbalta.cloudmanagerworker.service;

import com.cristianbalta.cloudmanagerworker.dto.PairDto;
import com.cristianbalta.cloudmanagerworker.dto.SystemInformationDto;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CloudService {

    private final Runtime runtime;

    CloudService() {
        this.runtime = Runtime.getRuntime();
    }

    public SystemInformationDto getSystemInformation() {
        SystemInformationDto systemInformationDto = new SystemInformationDto();
        systemInformationDto.setAvailableProcessors(runtime.availableProcessors());
        systemInformationDto.setFreeMemory(runtime.freeMemory() / 1024 / 10);
        return systemInformationDto;
    }

    public PairDto<String, Boolean> runCommand(String command) throws IOException {

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", command);
        File home = new File(System.getProperty("user.home"));
        builder.directory(home);
        Process process = builder.start();

        String line;

        StringBuilder result = new StringBuilder();
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            result.append(line).append("\n");
        }

        StringBuilder error = new StringBuilder();
        InputStream inErr = process.getErrorStream();
        BufferedReader readerErr = new BufferedReader(new InputStreamReader(inErr));
        while ((line = readerErr.readLine()) != null) {
            System.out.println(line);
            error.append(line).append("\n");
        }

        PairDto<String, Boolean> pairDto = new PairDto<>();
        if (!error.toString().isBlank()) {
            pairDto.setA(error.toString());
            pairDto.setB(false);
        } else {
            pairDto.setA(result.toString());
            pairDto.setB(true);
        }

        return pairDto;
    }
}
