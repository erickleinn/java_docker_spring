package com.devtest.test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.devtest.test.persistence_entities.LabelsEntity;
import com.devtest.test.repositories.LabelsRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LabelsServiceTest {

    @Autowired
    LabelsRepository repository;

    static Long labelId;

    @Test
    @Order(1)
    void testCreateLabel() {
        LabelsEntity label = new LabelsEntity();
        LabelsEntity result = new LabelsEntity();
        label.setCode("A1234");
        label.setDescription("testing");
        result = repository.save(label);
        labelId = result.getId();
        assertNotNull(repository.findById(result.getId()));
    }

    
    @Test
    @Order(2)
    void testGetAllLabels() {
        List<LabelsEntity> listLabels = repository.findAll();
        assertTrue(listLabels.size()>0);
    }

    @Test
    @Order(3)
    void testGetLabelById() {
        Optional<LabelsEntity> label = repository.findById(labelId);
        assertTrue(!label.isEmpty());
    }

    @Test
    @Order(4)
    void testUpdateLabel() {
        Optional<LabelsEntity> label = repository.findById(labelId);
        label.get().setCode("B4321");
        LabelsEntity result = repository.save(label.get());
        assertEquals(label.get(), result);
    }

    @Test
    @Order(5)
    void testDeleteLabel() {
        repository.deleteById(labelId);
        assertFalse(repository.existsById(labelId));
    }

}
