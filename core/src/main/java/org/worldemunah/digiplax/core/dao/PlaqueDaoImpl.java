package org.worldemunah.digiplax.core.dao;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Repository;
import org.worldemunah.digiplax.api.dao.PlaqueDao;
import org.worldemunah.digiplax.core.dao.base.BaseDaoImpl;
import org.worldemunah.digiplax.persistence.main.Plaque;

import java.util.List;
import java.util.Map;

/**
 * User: Ariel
 * Date: 4/11/2019
 */
@Repository
public class PlaqueDaoImpl extends BaseDaoImpl<Plaque> implements PlaqueDao {

    @Override
    public Plaque findByIdentifier(long identifier) {
        Map<String, Object> queryParams = ImmutableMap.of("identifier", identifier);
        return executeSingleResultNamedQuery("Plaque.findByIdentifier", queryParams);
    }

    @Override
    public List<Plaque> listByProject(Long projectId) {
        Map<String, Object> queryParams = ImmutableMap.of("projectId", projectId);
        return executeMultiResultNamedQuery("Plaque.listByProject", queryParams);
    }
}
