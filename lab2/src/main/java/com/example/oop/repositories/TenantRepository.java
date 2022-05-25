package com.example.oop.repositories;

import com.example.oop.entities.TenantEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepositoryImplementation<TenantEntity, Long> {
    Optional<TenantEntity> findByUsername(String username);
}
