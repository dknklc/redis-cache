package com.dekankilic.rediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class RedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheApplication.class, args);
	}

}
