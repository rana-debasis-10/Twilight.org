package com.twilight.ecommerceplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class }
)

public class EcommercePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePlatformApplication.class, args);
	}

}
