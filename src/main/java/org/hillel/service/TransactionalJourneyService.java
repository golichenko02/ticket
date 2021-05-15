package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.CommonRepository;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService extends CommonService<JourneyEntity, Long>{

    @Autowired
    JourneyRepository journeyRepository;

    public TransactionalJourneyService(CommonRepository<JourneyEntity, Long> repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(Long id, boolean withDependencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getCommonInfo().getName();
            journeyEntity.getStops().size();
        }
        return byId;
    }


}
