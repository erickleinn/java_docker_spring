package com.devtest.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devtest.test.business_entities.LabelBusinessEntity;
import com.devtest.test.persistence_entities.LabelsEntity;
import com.devtest.test.repositories.LabelsRepository;
import com.devtest.test.utils.mappers.LabelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    
    @Autowired
    LabelsRepository repository;

    @Autowired
    LabelMapper mapper;

    public List<LabelBusinessEntity> findAll(LabelBusinessEntity labelBusinessEntity){
        List<LabelsEntity> labelList = repository.findAll();
        if(labelList.isEmpty() || labelList == null)
            return new ArrayList<>();        
        return labelList.stream().map(mapper::map).collect(Collectors.toList());
    }

    public Optional<LabelBusinessEntity> findById(Long id){
        Optional<LabelsEntity> label = repository.findById(id);
        if(label.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.map(label.get()));
    }

    public Optional<LabelBusinessEntity> create(LabelBusinessEntity labelBusinessEntity){
        LabelsEntity persist = repository.save(mapper.map(labelBusinessEntity));
        if(persist == null){
            return Optional.empty();
        }
        return Optional.of(mapper.map(persist));
    }

    public Optional<LabelBusinessEntity> update(LabelBusinessEntity labelBusinessEntity, Long id){
        Optional<LabelsEntity> checkIfExists = repository.findById(id);
        if(checkIfExists.isEmpty())
            return Optional.empty();
        LabelsEntity updateEntity = new LabelsEntity();
        updateEntity = mapper.map(labelBusinessEntity);
        updateEntity.setId(id);
        return Optional.of(mapper.map(repository.save(updateEntity)));
    }

    public Optional<LabelBusinessEntity> delete(Long id){
        Optional<LabelsEntity> checkIfExists = repository.findById(id);
        if(checkIfExists.isEmpty())
            return Optional.empty();
        repository.delete(checkIfExists.get());
        return Optional.of(mapper.map(checkIfExists.get()));
    }

}
