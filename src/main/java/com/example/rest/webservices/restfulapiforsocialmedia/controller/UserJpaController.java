package com.example.rest.webservices.restfulapiforsocialmedia.controller;

import com.example.rest.webservices.restfulapiforsocialmedia.entity.PostEntity;
import com.example.rest.webservices.restfulapiforsocialmedia.entity.UserEntity;
import com.example.rest.webservices.restfulapiforsocialmedia.exception.UserNotFoundException;
import com.example.rest.webservices.restfulapiforsocialmedia.repository.PostRepository;
import com.example.rest.webservices.restfulapiforsocialmedia.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<UserEntity> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<UserEntity> retrieveUserById(@PathVariable int id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        EntityModel<UserEntity> entityModel = EntityModel.of(user.get());
        entityModel.add(link.withSelfRel());
        return entityModel;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<PostEntity> retrievePostsForUser(@PathVariable int id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        return user.get().getPostEntityList();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<PostEntity> createPost(@PathVariable int id, @Valid @RequestBody PostEntity post) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        post.setUser(user.get());
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
