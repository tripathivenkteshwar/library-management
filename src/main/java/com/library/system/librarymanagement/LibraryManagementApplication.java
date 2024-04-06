package com.library.system.librarymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.library.system.librarymanagement.config",
		"com.library.system.librarymanagement.aspect",
		"com.library.system.librarymanagement.service",
		"com.library.system.librarymanagement.controller"})

public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

}
