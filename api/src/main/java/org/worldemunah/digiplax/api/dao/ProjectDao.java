package org.worldemunah.digiplax.api.dao;

import org.worldemunah.digiplax.persistence.main.Project;

import java.util.List;

/**
 * User: Ariel
 * Date: 9/3/2019
 */
public interface ProjectDao extends BaseDao<Project> {

    List<Project> listAll();

    Project findByName(String name);
}
