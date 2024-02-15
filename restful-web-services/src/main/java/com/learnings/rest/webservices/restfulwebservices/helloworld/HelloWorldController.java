package com.learnings.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;


//REST API
/**
 * Retrieve all
 * GET/Users
 * Create
 * POST/users
 * retrive one
 * GET/users/{id}-> /users/1
 * delete- users/{id}
 *
 * posts
 * Get all posts of one user get/users/{id}/posts
 *
 * create a post for a user
 * users/{id}/post
 * retrive details of post
 * get/users/{id}/posts/{post_id}
 *
 *
 * take care of plurals
 * with plurals are much better to read and understand
 */

/**
 * Get-retrieve resources
 * Post-create new
 * Put-update existing
 * Delete-delete a resource
 * Patch - to update a part of res.
 */
@RestController
public class HelloWorldController {
    //@RequestMapping(method = RequestMethod.GET,path = "/helloWorld")
    @GetMapping(path = "/helloWorld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/helloWorldBean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Welcome to learning world");
    }

    //path parameters /user/{id}. Most of the URIs has path parameters
    //So Here in above uri {id} is a path parameter
    @GetMapping(path = "/hello-World/path-Variable/{msg}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String msg){
        return new HelloWorldBean("Hello -"+msg);
    }
}
