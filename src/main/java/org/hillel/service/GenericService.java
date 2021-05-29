package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.service.query_info.PaginationInfo;
import org.hillel.service.query_info.QueryType;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface GenericService<E, ID> {
    E createOrUpdate(final E entity);

    void remove(E vehicleEntity);

    void removeById(ID id);

    Collection<E> findByIds(ID... ids);

    default Collection<E> findAllByName(String name) {
        return Collections.emptyList();
    }

    Collection<E> findAll(QueryType queryType, PaginationInfo paginationInfo);

    Collection<E> findAll(PaginationInfo paginationInfo);

    Optional<E> findById(ID id);

    Collection<E> findAllAsNative(PaginationInfo paginationInfo);

    Collection<E> findAllAsNamed(PaginationInfo paginationInfo);

    Collection<E> findAllAsCriteria(PaginationInfo paginationInfo);

    Collection<E> findAllAsStoredProcedure();

    default Optional<E> findById(ID id, boolean withDependencies) {
        return Optional.empty();
    }

    default Collection<VehicleEntity> findWithMaxFreeSeats() {
        return Collections.emptyList();
    }

    default Collection<VehicleEntity> findWithMinFreeSeats() {
        return Collections.emptyList();
    }
}

