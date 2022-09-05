package com.devtest.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devtest.test.business_entities.LabelBusinessEntity;
import com.devtest.test.persistence_entities.LabelsEntity;
import com.devtest.test.services.LabelService;

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
@RequestMapping("/labels")
@Slf4j
public class LabelsController {

    @Autowired
    LabelService service;

    @GetMapping("/findAll")
    ResponseEntity<List<LabelBusinessEntity>> getAllLabels(LabelBusinessEntity label){
        try{
            List<LabelBusinessEntity> listLabels = new ArrayList<>();
            listLabels = service.findAll(label);
            if(listLabels.isEmpty()){
                log.error("No labels found");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Returning all the existing labels");
            return ResponseEntity.ok(listLabels);
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findById/{id}")
    ResponseEntity<LabelBusinessEntity> getLabelById(@PathVariable("id") Long id){
        try{
            Optional<LabelBusinessEntity> label = service.findById(id);
            if(label.isEmpty()){
                log.error("No label found for id: " + id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Returning label data of id: " + id);
            return ResponseEntity.ok(label.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    ResponseEntity<LabelBusinessEntity> createLabel(@RequestBody LabelBusinessEntity label){
        try{
            Optional<LabelBusinessEntity> persistedLabel = service.create(label);
            if(persistedLabel.isEmpty()){
                log.error("An error occured when persisting the label");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            log.info("Successfully created the label");
            return ResponseEntity.ok(persistedLabel.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<LabelBusinessEntity> updateLabel(@PathVariable Long id, @RequestBody LabelBusinessEntity updateLabel){
        try{
            if(id == null){
                log.error("Id might not be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }                
            Optional<LabelBusinessEntity> label = service.update(updateLabel, id);
            if(label.isEmpty()){
                log.error("No label found for id: " + id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Successfully updated the label from id: " + id);
            return ResponseEntity.ok(label.get());
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<LabelsEntity> deleteLabel(@PathVariable Long id){
        try{
            if(id == null){
                log.error("Id might not be null");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Optional<LabelBusinessEntity> label = service.delete(id);
            if(label.isEmpty()){
                log.error("No label found for id: " + id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            log.info("Successfully deleted the label from id: " + id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            log.error("An error occured during the method execution");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
