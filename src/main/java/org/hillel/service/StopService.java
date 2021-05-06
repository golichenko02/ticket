package org.hillel.service;

import org.hillel.persistence.entity.StopEntity;

public interface StopService {
    StopEntity createOrUpdateStop(final StopEntity stopEntity);

    void remove(StopEntity stopEntity);

    void removeById(Long id);
}
