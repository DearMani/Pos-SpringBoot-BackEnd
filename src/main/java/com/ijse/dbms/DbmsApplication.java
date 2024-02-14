package com.ijse.dbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbmsApplication.class, args);
		System.out.println("Hellow world");
	}

}