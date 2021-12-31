package com.xurl.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.xurl")
public class XUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(XUrlApplication.class, args);
	}

}
