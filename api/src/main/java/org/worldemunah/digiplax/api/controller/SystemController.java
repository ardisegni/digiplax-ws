package org.worldemunah.digiplax.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/system")
@CrossOrigin
public class SystemController extends BaseController {

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Welcome to Digiplax!");
    }

    @GetMapping(value = "/version")
    public ResponseEntity<?> version() {
        return ResponseEntity.ok("N/A");
    }
}
