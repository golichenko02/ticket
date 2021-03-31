package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalJourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Transactional
    public Long createJourney(final JourneyEntity journeyEntity){
        //todo: chek
        return journeyRepository.create(journeyEntity);
    }
}
