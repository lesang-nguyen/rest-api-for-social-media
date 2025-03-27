package com.example.rest.webservices.restfulapiforsocialmedia.controller;

import com.example.rest.webservices.restfulapiforsocialmedia.entity.UserEntity;
import com.example.rest.webservices.restfulapiforsocialmedia.exception.UserNotFoundException;
import com.example.rest.webservices.restfulapiforsocialmedia.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<UserEntity> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "users/{id}")
    public UserEntity retrieveUserById(@PathVariable int id) {
        var user = userDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userDaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
