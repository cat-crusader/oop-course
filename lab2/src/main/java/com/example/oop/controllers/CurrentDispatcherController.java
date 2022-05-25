package com.example.oop.controllers;

import com.example.oop.services.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrentDispatcherController {
    private final DispatcherService dispatcherService;

    @GetMapping("/current-dispatcher")
    public Object doGet() {
        return dispatcherService.getCurrentDispatcher();
    }
}
