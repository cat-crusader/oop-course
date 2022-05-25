package com.example.oop.services;

import com.example.oop.dto.response.CurrentDispatcherResponse;
import com.example.oop.entities.DispatcherEntity;
import com.example.oop.repositories.DispatcherRepository;

import javax.servlet.http.HttpServletRequest;

public class DispatcherService {
    private final AuthorizationService authorizationService = new AuthorizationService();
    private final DispatcherRepository dispatcherRepository = new DispatcherRepository();

    public CurrentDispatcherResponse getCurrentDispatcher(HttpServletRequest request) {
        String username = authorizationService.getUsername(request);
        DispatcherEntity dispatcherEntity = dispatcherRepository.findByUsername(username);
        return new CurrentDispatcherResponse(dispatcherEntity.getId(), dispatcherEntity.getUsername());
    }
}
