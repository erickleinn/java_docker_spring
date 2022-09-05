package com.devtest.test.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devtest.test.business_entities.CasesBusinessEntity;
import com.devtest.test.business_entities.DoctorBusinessEntity;
import com.devtest.test.business_entities.LabelBusinessEntity;
import com.devtest.test.persistence_entities.CasesEntity;
import com.devtest.test.repositories.CasesRepository;
import com.devtest.test.utils.mappers.CasesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CaseService {
    
    @Autowired
    CasesRepository casesRepository;

    @Autowired
    LabelService labelService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    CasesMapper mapper;

    public List<CasesBusinessEntity> findAll(CasesBusinessEntity casesBusinessEntity){
        List<CasesEntity> listCases = casesRepository.findAll();
        if(listCases.isEmpty() || listCases == null)
            return new ArrayList<>();
        List<CasesBusinessEntity> casesBusinessEntities = new ArrayList<>();
        listCases.forEach(c ->{
            CasesBusinessEntity casesEntity = new CasesBusinessEntity();
            casesEntity = mapper.map(c);
            Optional<LabelBusinessEntity> labelBusinessEntity = c.getLabelId() != null ? labelService.findById(c.getLabelId()) : Optional.empty();
            casesEntity.setLabel(!labelBusinessEntity.isEmpty() ? labelBusinessEntity.get() : null);
            Optional<DoctorBusinessEntity> doctorBusinessEntity = c.getDoctorId() != null ? doctorService.findById(c.getDoctorId()) : Optional.empty();
            casesEntity.setDoctor(!doctorBusinessEntity.isEmpty() ? doctorBusinessEntity.get() : null);
            casesBusinessEntities.add(casesEntity);
        });
        return casesBusinessEntities;
    }

    public Optional<CasesBusinessEntity> create(CasesBusinessEntity casesBusinessEntity){
        CasesEntity mappedEntity = mapper.map(casesBusinessEntity);
        mappedEntity.setLastUpdated(LocalDateTime.now());
        CasesEntity persist = casesRepository.save(mappedEntity);
        if(persist == null)
            return Optional.empty();
        return findById(persist.getId());
    }

    public List<CasesBusinessEntity> findAllByLabelId(Long labelId){
        List<CasesEntity> listCases = casesRepository.findAllByLabelId(labelId);
        if(listCases.isEmpty() || listCases == null)
            return new ArrayList<>();
        List<CasesBusinessEntity> casesBusinessEntities = new ArrayList<>();
        listCases.forEach(c ->{
            CasesBusinessEntity casesEntity = new CasesBusinessEntity();
            casesEntity = mapper.map(c);
            Optional<LabelBusinessEntity> labelBusinessEntity = c.getLabelId() != null ? labelService.findById(c.getLabelId()) : Optional.empty();
            casesEntity.setLabel(!labelBusinessEntity.isEmpty() ? labelBusinessEntity.get() : null);
            Optional<DoctorBusinessEntity> doctorBusinessEntity = c.getDoctorId() != null ? doctorService.findById(c.getDoctorId()) : Optional.empty();
            casesEntity.setDoctor(!doctorBusinessEntity.isEmpty() ? doctorBusinessEntity.get() : null);
            casesBusinessEntities.add(casesEntity);
        });
        return casesBusinessEntities;
    }

    public Optional<CasesBusinessEntity> findById(Long id){
        Optional<CasesEntity> result = casesRepository.findById(id);
        if(result.isEmpty())
            return Optional.empty();
        CasesBusinessEntity casesEntity = new CasesBusinessEntity();
        casesEntity = mapper.map(result.get());
        Optional<LabelBusinessEntity> labelBusinessEntity = result.get().getLabelId() != null ? labelService.findById(result.get().getLabelId()) : Optional.empty();
        casesEntity.setLabel(!labelBusinessEntity.isEmpty() ? labelBusinessEntity.get() : null);
        Optional<DoctorBusinessEntity> doctorBusinessEntity = result.get().getDoctorId() != null ? doctorService.findById(result.get().getDoctorId()) : Optional.empty();
        casesEntity.setDoctor(!doctorBusinessEntity.isEmpty() ? doctorBusinessEntity.get() : null);
        return Optional.of(casesEntity);
    }

    public Optional<CasesBusinessEntity> update(CasesBusinessEntity casesBusinessEntity, Long id){
        Optional<CasesEntity> checkIfExists = casesRepository.findById(id);
        if(checkIfExists.isEmpty())
            return Optional.empty();
        CasesEntity updateEntity = new CasesEntity();
        updateEntity = mapper.map(casesBusinessEntity);
        updateEntity.setId(id);
        updateEntity.setLastUpdated(LocalDateTime.now());
        CasesEntity update = casesRepository.save(updateEntity);
        return findById(update.getId());
    }

    public Optional<CasesBusinessEntity> delete(Long id){
        Optional<CasesEntity> checkIfExists = casesRepository.findById(id);
        if(checkIfExists.isEmpty())
            return Optional.empty();
        casesRepository.delete(checkIfExists.get());
        return Optional.of(mapper.map(checkIfExists.get()));
    }

}
