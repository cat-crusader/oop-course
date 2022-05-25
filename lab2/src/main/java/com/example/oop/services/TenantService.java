package com.example.oop.services;

import com.example.oop.dto.response.CurrentTenantResponse;
import com.example.oop.entities.TenantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final AuthorizationService authorizationService;

    public CurrentTenantResponse getCurrentTenant() {
        TenantEntity dispatcherEntity = authorizationService.getCurrentTenant();
        return new CurrentTenantResponse(dispatcherEntity.getId(), dispatcherEntity.getUsername());
    }
}
