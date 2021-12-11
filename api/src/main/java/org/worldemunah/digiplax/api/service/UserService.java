package org.worldemunah.digiplax.api.service;

import org.worldemunah.digiplax.api.model.UserDetailsModel;

import java.util.List;

/**
 * User: Ariel
 * Date: 5/29/2019
 */
public interface UserService {

    List<UserDetailsModel> getUsers();

    void createUser(UserDetailsModel userDetailsModel);

    void updateUser(Long id, UserDetailsModel userDetailsModel);

    void deleteUser(Long id);
}
