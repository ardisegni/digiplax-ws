package org.worldemunah.digiplax.core.dao;

import org.springframework.stereotype.Repository;
import org.worldemunah.digiplax.api.dao.BackgroundTypeDao;
import org.worldemunah.digiplax.core.dao.base.BaseDaoImpl;
import org.worldemunah.digiplax.persistence.main.BackgroundType;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Ariel
 * Date: 5/29/2019
 */
@Repository
public class BackgroundTypeDaoImpl extends BaseDaoImpl<BackgroundType> implements BackgroundTypeDao {

    @Override
    public BackgroundType findByCode(int code) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("code", code);
        return executeSingleResultNamedQuery("BackgroundType.findByCode", queryParams);
    }
}
