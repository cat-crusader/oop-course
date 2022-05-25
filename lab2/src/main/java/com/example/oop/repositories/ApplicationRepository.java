package com.example.oop.repositories;

import com.example.oop.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepositoryImplementation<ApplicationEntity, Long> {
}
