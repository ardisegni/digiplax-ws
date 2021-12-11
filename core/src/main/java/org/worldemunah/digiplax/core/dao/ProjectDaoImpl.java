package org.worldemunah.digiplax.core.dao;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Repository;
import org.worldemunah.digiplax.api.dao.ProjectDao;
import org.worldemunah.digiplax.core.dao.base.BaseDaoImpl;
import org.worldemunah.digiplax.persistence.main.Project;

import java.util.List;
import java.util.Map;

/**
 * User: Ariel
 * Date: 9/3/2019
 */
@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {

    @Override
    public List<Project> listAll() {
        return executeMultiResultNamedQuery("Project.listAll");
    }

    @Override
    public Project findByName(String name) {
        Map<String, Object> queryParams = ImmutableMap.of("name", name);
        return executeSingleResultNamedQuery("Project.findByName", queryParams);
    }
}
