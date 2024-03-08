package com.example.microservices.netflixeureknamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekNamingServerApplication.class, args);
	}

}
