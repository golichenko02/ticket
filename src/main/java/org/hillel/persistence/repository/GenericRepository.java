package org.hillel.persistence.repository;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    void removeById(ID id);

    void remove(E entity);

    Collection<E> findByIds(ID... ids);

    Collection<E> findAll();

    Collection<E> findAllAsNative();

    Collection<E> findAllAsNamed();

    Collection<E> findAllAsCriteria();

    Collection<E> findAllAsStoredProcedure();

}
