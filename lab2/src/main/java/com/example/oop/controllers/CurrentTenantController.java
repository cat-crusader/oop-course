package com.example.oop.controllers;

import com.example.oop.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrentTenantController {
    private final TenantService tenantService;

    @GetMapping("/current-tenant")
    public Object doGet() {
        return tenantService.getCurrentTenant();
    }
}
