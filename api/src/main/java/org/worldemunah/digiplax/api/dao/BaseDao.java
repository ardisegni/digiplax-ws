package org.worldemunah.digiplax.api.dao;

/**
 * User: Ariel
 * Date: 5/8/2019
 */
public interface BaseDao<E> {

    E insertEntity(E entity);

    E updateEntity(E plaque);

    E findById(Long id);

    void deleteEntity(E entity);
}
