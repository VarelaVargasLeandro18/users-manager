package com.lvv.users_manager;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UsersManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersManagerApplication.class, args);
	}

	@PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
    }

}