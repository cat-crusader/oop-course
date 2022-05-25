package com.example.oop.services;

import com.example.oop.entities.DispatcherEntity;
import com.example.oop.entities.TenantEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    public TenantEntity getCurrentTenant() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (TenantEntity) principal;
    }

    public DispatcherEntity getCurrentDispatcher() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (DispatcherEntity) principal;
    }
}
