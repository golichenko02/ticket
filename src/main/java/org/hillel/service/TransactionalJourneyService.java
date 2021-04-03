package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;

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

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo) {
        return null;
    }
}
