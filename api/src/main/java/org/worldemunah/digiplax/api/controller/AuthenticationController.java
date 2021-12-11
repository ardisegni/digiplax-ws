package org.worldemunah.digiplax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldemunah.digiplax.api.model.UserDetailsModel;
import org.worldemunah.digiplax.api.model.request.AuthenticationRequest;
import org.worldemunah.digiplax.api.service.AuthenticationService;

import javax.validation.Valid;

/**
 * User: Ariel
 * Date: 3/12/2019
 */
@RestControllerAdvice
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController extends BaseController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {
        UserDetailsModel userDetailsModel = authenticationService.authenticate(
                request.getUsername(), request.getPassword()
        );
        return ResponseEntity.ok().body(userDetailsModel);
    }
}
