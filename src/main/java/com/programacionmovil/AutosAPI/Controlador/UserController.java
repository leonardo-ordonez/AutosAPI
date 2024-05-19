/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.programacionmovil.AutosAPI.Controlador;

import com.programacionmovil.AutosAPI.Modelo.User;
import com.programacionmovil.AutosAPI.Servicio.UserService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        logger.info("Login attempt for user: " + user.getUsername());
        return userService.authenticate(user.getUsername(), user.getPassword())
                .map(authenticatedUser -> {
                    logger.info("Login successful for user: " + user.getUsername());
                    return ResponseEntity.ok(authenticatedUser);
                })
                .orElseGet(() -> {
                    logger.info("Login failed for user: " + user.getUsername());
                    return ResponseEntity.status(401).build();
                });
    }
}
