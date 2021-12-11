package org.worldemunah.digiplax.api.service;

import org.worldemunah.digiplax.api.model.UserDetailsModel;

/**
 * User: Ariel
 * Date: 5/8/2019
 */
public interface AuthenticationService {

    UserDetailsModel authenticate(String username, String password);
}
