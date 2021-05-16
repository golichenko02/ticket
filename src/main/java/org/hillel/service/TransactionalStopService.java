package org.hillel.service;

import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.CommonRepository;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("transactionalStopService")
public class TransactionalStopService extends CommonService<StopEntity, Long> {

    @Autowired
    private StopRepository stopRepository;

    public TransactionalStopService(CommonRepository<StopEntity, Long> repository) {
        super(repository);
    }

    @Override
    public Collection<StopEntity> findAllByName(String name) {
        return stopRepository.findAllByName(name);
    }
}
