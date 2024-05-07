package com.ecomrse.projectsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableEurekaClient
@SpringBootApplication
public class AllServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllServiceApplication.class, args);
	}

}
