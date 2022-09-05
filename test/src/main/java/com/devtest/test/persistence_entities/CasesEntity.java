package com.devtest.test.persistence_entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "cases")
@Data
public class CasesEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String description;

    @Column(name = "FK_LABEL_ID")
    private Long labelId;

    @Column(name = "FK_DOCTOR_ID")
    private Long doctorId;
    
    private LocalDateTime lastUpdated;

}
