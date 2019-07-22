package com.project.sharebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class SharebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharebookApplication.class, args);

	}

}
