package org.hillel.service;

import java.util.Collection;
import java.util.Optional;

public interface GenericService<E, ID> {
    E createOrUpdate(final E entity);

    void remove(E vehicleEntity);

    void removeById(ID id);

    Collection<E> findByIds(ID... ids);

    Collection<E> findAll(QueryType queryType);

    Collection<E> findAll();

    Optional<E> findById(ID id);

    Collection<E> findAllAsNative();

    Collection<E> findAllAsNamed();

    Collection<E> findAllAsCriteria();

    Collection<E> findAllAsStoredProcedure();

    default Optional<E> findById(ID id, boolean withDependencies) {
        return Optional.empty();
    }


}

