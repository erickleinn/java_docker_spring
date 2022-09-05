package com.devtest.test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.devtest.test.persistence_entities.CasesEntity;
import com.devtest.test.repositories.CasesRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CasesControllerTest {

    @Autowired
    CasesRepository repository;
    
    static Long caseId;
    @Test
    @Order(1)
    void testCreate() {
        CasesEntity casesEntity = new CasesEntity();
        CasesEntity result = new CasesEntity();
        casesEntity.setDescription("test description");
        casesEntity.setLastUpdated(LocalDateTime.now());
        result = repository.save(casesEntity);
        caseId = result.getId();
        assertNotNull(repository.findById(result.getId()));
    }

    @Test
    void testGetAllCases() {
        List<CasesEntity> listCases = repository.findAll();
        assertTrue(listCases.size()>0);
    }

    @Test
    void testGetAllById(){
        Optional<CasesEntity> cases = repository.findById(caseId);
        assertTrue(!cases.isEmpty());
    }

    @Test
    void testUpdateCase() {
        Optional<CasesEntity> cases = repository.findById(caseId);
        cases.get().setDescription("test update description");
        CasesEntity result = repository.save(cases.get());
        assertEquals(cases.get(), result);
    }

    @Test
    void testDeleteCase() {
        repository.deleteById(caseId);
        assertFalse(repository.existsById(caseId));
    }
}
