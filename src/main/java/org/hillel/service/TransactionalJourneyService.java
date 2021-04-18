package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Transactional
    @Override
    public Long createJourney(final JourneyEntity journeyEntity){
        //todo: chek
        return journeyRepository.create(journeyEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<JourneyEntity> getById(Long id, boolean withDependencies) {
        final  Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()){
           final JourneyEntity journeyEntity =  byId.get();
           journeyEntity.getVehicle().getCommonInfo().getName();
           journeyEntity.getStops().size();
        }
        return byId;
    }

    @Transactional
    @Override
    public void save(JourneyEntity journey) {
        journeyRepository.save(journey);
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo) {
        return null;
    }
}
