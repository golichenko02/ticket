package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface JourneyService {

    JourneyEntity createOrUpdateJourney(JourneyEntity journeyEntity);

    default Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }

    default Collection<Journey> find(String stationFrom, String stationTo) {
        return Collections.emptyList();
    }

    default Optional<JourneyEntity> findById(Long id, boolean withDependencies) {
        return Optional.empty();
    }

}

