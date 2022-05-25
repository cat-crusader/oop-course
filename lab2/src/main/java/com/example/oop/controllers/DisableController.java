package com.example.oop.controllers;

import com.example.oop.dto.request.DisableRequest;
import com.example.oop.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DisableController {
    private final ApplicationService applicationService;

    @PutMapping("/disable")
    public void doPut(@RequestBody DisableRequest request) {
        applicationService.disable(request);
    }
}
