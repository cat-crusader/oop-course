package com.example.oop.repositories;

import com.example.oop.entities.DispatcherEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispatcherRepository extends JpaRepositoryImplementation<DispatcherEntity, Long> {
    Optional<DispatcherEntity> findByUsername(String username);
}
