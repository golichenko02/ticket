package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface JourneyService {

    Long createJourney(JourneyEntity journeyEntity);

    Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo);
    Collection<Journey> find(String stationFrom, String stationTo);
}
