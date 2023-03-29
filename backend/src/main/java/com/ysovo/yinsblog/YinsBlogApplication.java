package com.ysovo.yinsblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.ysovo.yinsblog.dao")
@SpringBootApplication
@EnableScheduling
public class YinsBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(YinsBlogApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
