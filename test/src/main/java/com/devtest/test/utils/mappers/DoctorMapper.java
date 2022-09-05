package com.devtest.test.utils.mappers;

import com.devtest.test.business_entities.DoctorBusinessEntity;
import com.devtest.test.persistence_entities.DoctorsEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorsEntity map(DoctorBusinessEntity doctorBusinessEntity);

    DoctorBusinessEntity map(DoctorsEntity doctorsEntity);
}
