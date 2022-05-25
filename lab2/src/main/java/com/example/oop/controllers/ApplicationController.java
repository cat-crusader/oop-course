package com.example.oop.controllers;

import com.example.oop.dto.request.ApplicationRequest;
import com.example.oop.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/applications")
    public Object doGet() {
        return applicationService.findAll();
    }

    @PostMapping("/applications")
    public void doPost(@RequestBody ApplicationRequest body) {
        applicationService.insert(body);
    }
}
