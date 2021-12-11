package org.worldemunah.digiplax.core.dao;

import org.springframework.stereotype.Repository;
import org.worldemunah.digiplax.api.dao.BackofficeUserDao;
import org.worldemunah.digiplax.core.dao.base.BaseDaoImpl;
import org.worldemunah.digiplax.persistence.security.BackofficeUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ariel
 * Date: 4/24/2019
 */
@Repository
public class BackofficeUserDaoImpl extends BaseDaoImpl<BackofficeUser> implements BackofficeUserDao {

    public BackofficeUser findByEmailAddress(String emailAddress) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("emailAddress", emailAddress);
        return executeSingleResultNamedQuery("BackofficeUser.findByEmailAddress", queryParams);
    }

    @Override
    public List<BackofficeUser> listAll() {
        return executeMultiResultNamedQuery("BackofficeUser.listAll");
    }
}
