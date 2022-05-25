package com.example.oop.services;

import com.example.oop.dto.request.ApplicationRequest;
import com.example.oop.dto.request.DisableRequest;
import com.example.oop.dto.response.ApplicationResponse;
import com.example.oop.entities.ApplicationEntity;
import com.example.oop.repositories.ApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationService {
    private final ApplicationRepository applicationRepository = new ApplicationRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ApplicationResponse> findAll() {
        List<ApplicationEntity> entities = applicationRepository.finAll();
        List<ApplicationResponse> result = new ArrayList<>();
        for (ApplicationEntity entity : entities) {
            ApplicationResponse response =
                    new ApplicationResponse(
                            entity.getId(),
                            entity.getType(),
                            entity.getScale(),
                            entity.getDurationInDays(),
                            entity.getAccepted());
            result.add(response);
        }
        return result;
    }

    public void insert(HttpServletRequest request) {
        try {
            ApplicationRequest body = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                    ApplicationRequest.class);
            applicationRepository.insert(body.getType(), body.getScale(), body.getDurationInDays());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disable(HttpServletRequest request) {
        try {
            DisableRequest body = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                    DisableRequest.class);
            applicationRepository.disable(body.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enable(HttpServletRequest request) {
        try {
            DisableRequest body = objectMapper.readValue(
                    request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                    DisableRequest.class);
            applicationRepository.enable(body.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
