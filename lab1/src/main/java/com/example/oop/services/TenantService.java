package com.example.oop.services;

import com.example.oop.dto.response.CurrentTenantResponse;
import com.example.oop.entities.DispatcherEntity;
import com.example.oop.repositories.TenantRepository;

import javax.servlet.http.HttpServletRequest;

public class TenantService {
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final TenantRepository tenantRepository = new TenantRepository();

    public CurrentTenantResponse getCurrentTenant(HttpServletRequest request) {
        String username = authorizationService.getUsername(request);
        DispatcherEntity dispatcherEntity = tenantRepository.findByUsername(username);
        return new CurrentTenantResponse(dispatcherEntity.getId(), dispatcherEntity.getUsername());
    }
}
