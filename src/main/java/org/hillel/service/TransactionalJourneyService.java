package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService implements JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Transactional
    @Override
    public JourneyEntity createOrUpdateJourney(final JourneyEntity journeyEntity) {
        return journeyRepository.createOrUpdate(journeyEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<JourneyEntity> findById(Long id, boolean withDependencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getCommonInfo().getName();
            journeyEntity.getStops().size();
        }
        return byId;
    }

    @Transactional
    @Override
    public void removeById(Long id) {
        journeyRepository.removeById(id);
    }

    @Transactional
    @Override
    public void remove(JourneyEntity entity) {
        journeyRepository.remove(entity);
    }
}
