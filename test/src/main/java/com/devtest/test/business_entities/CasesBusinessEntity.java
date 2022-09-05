package com.devtest.test.business_entities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CasesBusinessEntity {
    
    private Long id;

    private String description;

    private LabelBusinessEntity label;

    private DoctorBusinessEntity doctor;

    private LocalDateTime lastUpdated;
}
