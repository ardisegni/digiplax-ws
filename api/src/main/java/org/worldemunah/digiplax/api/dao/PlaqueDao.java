package org.worldemunah.digiplax.api.dao;

import org.worldemunah.digiplax.persistence.main.Plaque;

import java.util.List;

/**
 * User: Ariel
 * Date: 4/11/2019
 */
public interface PlaqueDao extends BaseDao<Plaque> {

    Plaque findByIdentifier(long identifier);

    List<Plaque> listByProject(Long projectId);
}
