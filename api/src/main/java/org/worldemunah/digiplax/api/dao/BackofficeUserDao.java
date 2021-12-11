package org.worldemunah.digiplax.api.dao;

import org.worldemunah.digiplax.persistence.security.BackofficeUser;

import java.util.List;

/**
 * User: Ariel
 * Date: 4/24/2019
 */
public interface BackofficeUserDao extends BaseDao<BackofficeUser> {

    BackofficeUser findByEmailAddress(String emailAddress);

    List<BackofficeUser> listAll();
}
