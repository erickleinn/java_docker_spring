package com.devtest.test.utils.mappers;



import com.devtest.test.business_entities.LabelBusinessEntity;
import com.devtest.test.persistence_entities.LabelsEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelMapper {
    
    LabelsEntity map(LabelBusinessEntity labelBusinessEntity);

    LabelBusinessEntity map(LabelsEntity labelsEntity);

}
