package com.devtest.test.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devtest.test.business_entities.DoctorBusinessEntity;
import com.devtest.test.repositories.DoctorsRepository;
import com.devtest.test.services.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/doctors")
@Slf4j
public class DoctorsController {
    @Autowired
    DoctorsRepository repository;

    @Autowired
    DoctorService service;

    @GetMapping("/findAll")
    public ResponseEntity<List<DoctorBusinessEntity>> getAllDoctors(DoctorBusinessEntity doctorBusinessEntity){
        try{
            List<DoctorBusinessEntity> listDoctors = new ArrayList<>();
            listDoctors = service.findAll(doctorBusinessEntity);
            if(listDoctors.isEmpty()){
                log.error("No doctors found on the database");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Returning all existing doctor registers");
            return ResponseEntity.ok(listDoctors);
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorBusinessEntity> create(@RequestBody DoctorBusinessEntity doctorBusinessEntity){
        try{
            Optional<DoctorBusinessEntity> result = service.create(doctorBusinessEntity);
            if(result.isEmpty()){
                log.error("An error occured when persisting the doctor");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            log.info("Successfully created the doctor registry");
            return ResponseEntity.ok(result.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
