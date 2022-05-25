package com.example.oop.services;

import com.example.oop.dto.response.CurrentDispatcherResponse;
import com.example.oop.entities.DispatcherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatcherService {
    private final AuthorizationService authorizationService;

    public CurrentDispatcherResponse getCurrentDispatcher() {
        DispatcherEntity dispatcherEntity = authorizationService.getCurrentDispatcher();
        return new CurrentDispatcherResponse(dispatcherEntity.getId(), dispatcherEntity.getUsername());
    }
}
