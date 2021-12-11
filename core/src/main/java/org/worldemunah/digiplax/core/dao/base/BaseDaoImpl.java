package org.worldemunah.digiplax.core.dao.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.worldemunah.digiplax.api.dao.BaseDao;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ariel
 * Date: 4/24/2019
 */
@Transactional
public class BaseDaoImpl<E> implements BaseDao<E> {

    private static final Logger log = LogManager.getLogger(BaseDaoImpl.class);

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    private final Class<E> entityClass;

    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    protected List<E> executeMultiResultNamedQuery(String namedQuery) {
        return executeMultiResultNamedQuery(namedQuery, new HashMap<>());
    }

    protected List<E> executeMultiResultNamedQuery(String namedQuery, Map<String, Object> queryParams) {
        TypedQuery<E> query = prepareQuery(namedQuery, queryParams);
        return query.getResultList();
    }

    private TypedQuery<E> prepareQuery(String namedQuery, Map<String, Object> queryParams) {
        TypedQuery<E> query = em.createNamedQuery(namedQuery, entityClass);
        for (Map.Entry<String, Object> queryParam : queryParams.entrySet()) {
            query.setParameter(queryParam.getKey(), queryParam.getValue());
        }
        return query;
    }

    protected E executeSingleResultNamedQuery(String namedQuery, Map<String, Object> queryParams) {
        Query query = prepareQuery(namedQuery, queryParams);
        try {
            return (E) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (EntityNotFoundException | IllegalStateException e) {
            log.error("Failed executing query: " + namedQuery, e);
            throw new RuntimeException("Failed executing query: " + namedQuery, e);
        } catch (Exception e) {
            throw new RuntimeException("Unknown error while executing query: " + namedQuery, e);
        }
    }

    @Override
    public E insertEntity(E entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public E updateEntity(E entity) {
        em.merge(entity);
        em.flush();
        return entity;
    }

    @Override
    public E findById(Long id) {
        return em.find(entityClass, id);
    }

    @Override
    public void deleteEntity(E entity) {
        em.remove(entity);
    }
}
