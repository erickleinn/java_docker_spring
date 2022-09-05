package com.devtest.test.repositories;

import java.util.List;

import com.devtest.test.persistence_entities.CasesEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasesRepository extends JpaRepository<CasesEntity, Long>{
    List<CasesEntity> findAllByLabelId(Long labelId);
}
