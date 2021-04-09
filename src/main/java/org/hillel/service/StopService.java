package org.hillel.service;

import org.hillel.persistence.entity.StopEntity;

public interface StopService {
    Long createStop(final StopEntity stopEntity);
}
