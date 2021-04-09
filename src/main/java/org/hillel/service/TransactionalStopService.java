package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("transactionalStopService")
public class TransactionalStopService implements StopService{

    @Autowired
    private  StopRepository stopRepository;

    @Transactional
    @Override
    public Long createStop(StopEntity stopEntity) {
        //todo: chek
        return stopRepository.create(stopEntity);
    }
}
