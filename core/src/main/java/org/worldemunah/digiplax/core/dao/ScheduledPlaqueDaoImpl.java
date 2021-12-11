package org.worldemunah.digiplax.core.dao;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.worldemunah.digiplax.api.dao.ScheduledPlaqueDao;
import org.worldemunah.digiplax.core.dao.base.BaseDaoImpl;
import org.worldemunah.digiplax.persistence.main.ScheduledPlaque;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ariel
 * Date: 4/11/2019
 */
@Repository
public class ScheduledPlaqueDaoImpl extends BaseDaoImpl<ScheduledPlaque> implements ScheduledPlaqueDao {

    @Override
    public List<ScheduledPlaque> listActive(Long projectId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("currentDateTime", DateTime.now().toDate());
        queryParams.put("projectId", projectId);
        return executeMultiResultNamedQuery("ScheduledPlaque.listActive", queryParams);
    }

    @Override
    public List<ScheduledPlaque> listScheduled() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("currentDateTime", DateTime.now().toDate());
        return executeMultiResultNamedQuery("ScheduledPlaque.listScheduled", queryParams);
    }

    @Override
    public List<ScheduledPlaque> listPast() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("currentDateTime", DateTime.now().toDate());
        return executeMultiResultNamedQuery("ScheduledPlaque.listPast", queryParams);
    }

    @Override
    public List<ScheduledPlaque> listByPlaque(Long plaqueId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("plaqueId", plaqueId);
        return executeMultiResultNamedQuery("ScheduledPlaque.listByPlaque", queryParams);
    }
}
