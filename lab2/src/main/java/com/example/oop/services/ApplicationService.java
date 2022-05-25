package com.example.oop.services;

import com.example.oop.dto.request.ApplicationRequest;
import com.example.oop.dto.request.DisableRequest;
import com.example.oop.dto.response.ApplicationResponse;
import com.example.oop.entities.ApplicationEntity;
import com.example.oop.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public List<ApplicationResponse> findAll() {
        List<ApplicationEntity> entities = applicationRepository.findAll();
        List<ApplicationResponse> result = new ArrayList<>();
        for (ApplicationEntity entity : entities) {
            ApplicationResponse response =
                    new ApplicationResponse(
                            entity.getId(),
                            entity.getType(),
                            entity.getScale(),
                            entity.getDurationInDays(),
                            entity.getIsAccepted());
            result.add(response);
        }
        return result;
    }

    public void insert(ApplicationRequest body) {
        applicationRepository.save(ApplicationEntity.builder()
                .type(body.getType())
                .scale(body.getScale())
                .durationInDays(body.getDurationInDays())
                .build());
    }

    public void disable(DisableRequest body) {
        ApplicationEntity byId = applicationRepository.getById(body.getId());
        byId.setIsAccepted(false);
        applicationRepository.save(byId);
    }

    public void enable(DisableRequest body) {
        ApplicationEntity byId = applicationRepository.getById(body.getId());
        byId.setIsAccepted(true);
        applicationRepository.save(byId);
    }
}
