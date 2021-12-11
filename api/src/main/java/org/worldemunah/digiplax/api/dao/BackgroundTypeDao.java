package org.worldemunah.digiplax.api.dao;

import org.worldemunah.digiplax.persistence.main.BackgroundType;

/**
 * User: Ariel
 * Date: 5/29/2019
 */
public interface BackgroundTypeDao {

    BackgroundType findByCode(int code);
}
