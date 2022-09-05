package com.devtest.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devtest.test.business_entities.CasesBusinessEntity;
import com.devtest.test.persistence_entities.CasesEntity;
import com.devtest.test.services.CaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cases")
@Slf4j
public class CasesController {

    @Autowired
    CaseService service;

    @GetMapping("/findAll")
    public ResponseEntity<List<CasesBusinessEntity>> getAllCases(CasesBusinessEntity casesBusinessEntity){
        try{
            List<CasesBusinessEntity> listCases = new ArrayList<>();
            listCases = service.findAll(casesBusinessEntity);
            if(listCases.isEmpty()){
                log.error("No cases found on the database");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Returning all the existing cases on the databse");
            return ResponseEntity.ok(listCases);
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CasesBusinessEntity> create(@RequestBody CasesBusinessEntity casesBusinessEntity){
        try{
            Optional<CasesBusinessEntity> result = service.create(casesBusinessEntity);
            if(result.isEmpty()){
                log.error("An error occured when persisting the case");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            log.info("Successfully created the case registry");
            return ResponseEntity.ok(result.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findAllByLabel/{labelId}")
    public ResponseEntity<List<CasesBusinessEntity>> getAllByLabel(@PathVariable("labelId") Long labelId){
        try{
            List<CasesBusinessEntity> listCases = new ArrayList<>();
            listCases = service.findAllByLabelId(labelId);
            if(listCases.isEmpty()){
                log.error("No cases found on the database");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Returning all the existing cases on the databse");
            return ResponseEntity.ok(listCases);
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CasesBusinessEntity> getAllById(@PathVariable("id") Long id){
        try{
            Optional<CasesBusinessEntity> casesBusinessEntity = service.findById(id);
            if(casesBusinessEntity.isEmpty()){
                log.error("No cases found on the database");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Returning all the existing cases on the databse");
            return ResponseEntity.ok(casesBusinessEntity.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<CasesBusinessEntity> updateCase(@PathVariable Long id, @RequestBody CasesBusinessEntity updateCase){
        try{
            if(id == null){
                log.error("Id might not be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }                
            Optional<CasesBusinessEntity> casesEntity = service.update(updateCase, id);
            if(casesEntity.isEmpty()){
                log.error("No case found for id: " + id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Successfully updated the case from id: " + id);
            return ResponseEntity.ok(casesEntity.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<CasesEntity> deleteCase(@PathVariable Long id){
        try{
            if(id == null){
                log.error("Id might not be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Optional<CasesBusinessEntity> caseEntity = service.delete(id);
            if(caseEntity.isEmpty()){
                log.error("No case found for id: " + id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Successfully deleted the case from id: " + id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
