package com.devtest.test.repositories;

import com.devtest.test.persistence_entities.DoctorsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<DoctorsEntity, Long> {
    
}
