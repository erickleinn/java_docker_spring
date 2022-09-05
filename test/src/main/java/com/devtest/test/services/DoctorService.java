package com.devtest.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devtest.test.business_entities.DoctorBusinessEntity;
import com.devtest.test.persistence_entities.DoctorsEntity;
import com.devtest.test.repositories.DoctorsRepository;
import com.devtest.test.utils.mappers.DoctorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorsRepository repository;

    @Autowired
    DoctorMapper mapper;

    public List<DoctorBusinessEntity> findAll(DoctorBusinessEntity doctorBusinessEntity){
        List<DoctorsEntity> doctorsEntities = repository.findAll();
        if(doctorsEntities.isEmpty() || doctorsEntities == null)
            return new ArrayList<>();
        return doctorsEntities.stream().map(mapper::map).collect(Collectors.toList());
    }

    public Optional<DoctorBusinessEntity> create(DoctorBusinessEntity doctorBusinessEntity){
        DoctorsEntity persist = repository.save(mapper.map(doctorBusinessEntity));
        if(persist == null)
            return Optional.empty();
        return Optional.of(mapper.map(persist));
    }

    public Optional<DoctorBusinessEntity> findById(Long id){
        Optional<DoctorsEntity> result = repository.findById(id);
        if(result.isEmpty())
            return Optional.empty();
        return Optional.of(mapper.map(result.get()));
    }


}
