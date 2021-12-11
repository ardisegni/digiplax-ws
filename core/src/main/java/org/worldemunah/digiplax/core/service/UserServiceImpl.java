package org.worldemunah.digiplax.core.service;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldemunah.digiplax.api.dao.BackofficeUserDao;
import org.worldemunah.digiplax.api.model.UserDetailsModel;
import org.worldemunah.digiplax.api.service.UserService;
import org.worldemunah.digiplax.persistence.security.BackofficeUser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Ariel
 * Date: 5/29/2019
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BackofficeUserDao backofficeUserDao;

    @Override
    public List<UserDetailsModel> getUsers() {
        List<BackofficeUser> users = backofficeUserDao.listAll();
        return users.stream()
                .map(user -> new UserDetailsModel(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmailAddress(),
                        user.getPassword(),
                        user.getPhoneNum(),
                        user.isSuperUserFlag()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserDetailsModel userDetailsModel) {
        String emailAddress = userDetailsModel.getEmailAddress();
        BackofficeUser backofficeUser = backofficeUserDao.findByEmailAddress(emailAddress);
        if (backofficeUser != null) {
            throw new RuntimeException("User with this email address already exists");
        }

        backofficeUser = new BackofficeUser(emailAddress);
        backofficeUser.setFirstName(userDetailsModel.getFirstName());
        backofficeUser.setLastName(userDetailsModel.getLastName());
        backofficeUser.setPassword(userDetailsModel.getPassword());
        Boolean superUser = userDetailsModel.isSuperUser();
        backofficeUser.setSuperUserFlag(BooleanUtils.toBooleanDefaultIfNull(superUser, false));

        backofficeUserDao.insertEntity(backofficeUser);
    }

    @Override
    public void updateUser(Long id, UserDetailsModel userDetailsModel) {
        BackofficeUser backofficeUser = backofficeUserDao.findById(id);
        if (backofficeUser == null) {
            throw new RuntimeException("User with id " + id + " does not exist");
        }

        String firstName = userDetailsModel.getFirstName();
        if (StringUtils.isNotEmpty(firstName)) {
            backofficeUser.setFirstName(firstName);
        }

        String lastName = userDetailsModel.getLastName();
        if (StringUtils.isNotEmpty(lastName)) {
            backofficeUser.setLastName(lastName);
        }

        String emailAddress = userDetailsModel.getEmailAddress();
        if (StringUtils.isNotEmpty(emailAddress)) {
            backofficeUser.setEmailAddress(emailAddress);
        }

        String password = userDetailsModel.getPassword();
        if (StringUtils.isNotEmpty(password)) {
            backofficeUser.setPassword(password);
        }

        String phoneNum = userDetailsModel.getPhoneNum();
        if (phoneNum != null) {
            backofficeUser.setPhoneNum(phoneNum);
        }

        Boolean superUser = userDetailsModel.isSuperUser();
        backofficeUser.setSuperUserFlag(BooleanUtils.toBooleanDefaultIfNull(superUser, false));

        backofficeUserDao.updateEntity(backofficeUser);
    }

    @Override
    public void deleteUser(Long id) {
        BackofficeUser backofficeUser = backofficeUserDao.findById(id);
        if (backofficeUser == null) {
            throw new RuntimeException("User with id " + id + " does not exist");
        }

        backofficeUserDao.deleteEntity(backofficeUser);
    }
}
