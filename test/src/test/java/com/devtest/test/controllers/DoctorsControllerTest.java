package com.devtest.test.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.devtest.test.persistence_entities.DoctorsEntity;
import com.devtest.test.repositories.DoctorsRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DoctorsControllerTest {
    
    @Autowired
    DoctorsRepository repository;

    @Test
    @Order(1)
    void testCreate() {
       DoctorsEntity doctors = new DoctorsEntity();
       DoctorsEntity result = new DoctorsEntity();
       doctors.setName("test doctor name");
       result = repository.save(doctors);
       assertNotNull(repository.findById(result.getId()));
    }

    @Test
    @Order(2)
    void testGetAllDoctors() {
        List<DoctorsEntity> doctorsList = repository.findAll();
        assertTrue(doctorsList.size()>0);
    }
}
