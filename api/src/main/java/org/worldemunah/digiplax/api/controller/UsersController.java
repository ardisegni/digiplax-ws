package org.worldemunah.digiplax.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldemunah.digiplax.api.model.UserDetailsModel;
import org.worldemunah.digiplax.api.service.UserService;

/**
 * User: Ariel
 * Date: 5/29/2019
 */
@RestControllerAdvice
@CrossOrigin
@RequestMapping(value = "/api")
public class UsersController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUsers")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDetailsModel userDetailsModel) {
        userService.createUser(userDetailsModel);
        return ResponseEntity.ok("Success");
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDetailsModel userDetailsModel) {
        userService.updateUser(id, userDetailsModel);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Success");
    }
}
