package com.learnings.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    /*
    In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans.
     A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.
     Otherwise, a bean is simply one of many objects in your application.
     */
    @Bean
    /*
    What is @bean used for?
Spring @Bean Annotation is applied on a method to specify that it returns a bean to be managed by Spring context.
Spring Bean annotation is usually declared in Configuration classes methods.
In this case, bean methods may reference other @Bean methods in the same class by calling them directly.
     */
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //1. All request should be authenticated
        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
//2. if request is not authenticated then web page should shown

        httpSecurity.httpBasic(Customizer.withDefaults());
        // 3. csrf() post and put disable();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }

}
