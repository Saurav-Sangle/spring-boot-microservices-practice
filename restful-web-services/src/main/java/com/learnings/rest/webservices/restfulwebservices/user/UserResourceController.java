package com.learnings.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResourceController {
    public UserDaoService userDaoService;

    public UserResourceController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable int id) {
        return userDaoService.findOne(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser=userDaoService.saveUser(user);
        /**
         * Here in below lines we send a 201 created response using ResponseEntity, now we are sending a location of URI, so in response header consumer can view URI and access details of created user
         */
        URI location= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

}
