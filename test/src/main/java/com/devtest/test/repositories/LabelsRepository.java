package com.devtest.test.repositories;

import com.devtest.test.persistence_entities.LabelsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelsRepository extends JpaRepository<LabelsEntity, Long> {
    
}
