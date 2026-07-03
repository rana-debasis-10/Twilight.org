package com.twilight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableKafka
@EnableWebSocket
public class TwilightApplication {
	public static void main(String[] args) {
		SpringApplication.run(TwilightApplication.class, args);
	}
}
