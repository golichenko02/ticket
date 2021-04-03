package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
@Component
public class StubJourneyServiceImpl implements JourneyService {

    @Override
    public Long createJourney(JourneyEntity journeyEntity) {
        return null;
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo) {
        return Collections.emptyList();
    }
}
