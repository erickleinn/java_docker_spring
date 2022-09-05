package com.devtest.test.utils.mappers;

import com.devtest.test.business_entities.CasesBusinessEntity;
import com.devtest.test.persistence_entities.CasesEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, LabelMapper.class})
public interface CasesMapper {

    @Mapping(source = "label.id", target = "labelId")
    @Mapping(source = "doctor.id", target = "doctorId")
    CasesEntity map(CasesBusinessEntity casesBusinessEntity);

    CasesBusinessEntity map(CasesEntity casesEntity);
}
