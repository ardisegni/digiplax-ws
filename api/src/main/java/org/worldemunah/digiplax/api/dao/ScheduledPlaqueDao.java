package org.worldemunah.digiplax.api.dao;

import org.worldemunah.digiplax.persistence.main.ScheduledPlaque;

import java.util.List;

/**
 * User: Ariel
 * Date: 4/11/2019
 */
public interface ScheduledPlaqueDao extends BaseDao<ScheduledPlaque> {

    List<ScheduledPlaque> listActive(Long projectId);

    List<ScheduledPlaque> listScheduled();

    List<ScheduledPlaque> listPast();

    List<ScheduledPlaque> listByPlaque(Long plaqueId);
}
