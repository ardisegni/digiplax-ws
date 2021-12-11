package org.worldemunah.digiplax.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldemunah.digiplax.api.dao.BackofficeUserDao;
import org.worldemunah.digiplax.api.model.UserDetailsModel;
import org.worldemunah.digiplax.api.service.AuthenticationService;
import org.worldemunah.digiplax.persistence.security.BackofficeUser;

/**
 * User: Ariel
 * Date: 5/8/2019
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private BackofficeUserDao backofficeUserDao;

    public UserDetailsModel authenticate(String username, String password) {
        BackofficeUser boUser = backofficeUserDao.findByEmailAddress(username);
        if (boUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!boUser.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return new UserDetailsModel(boUser.getFirstName(), boUser.getLastName());
    }
}
