package com.sft.nonhyeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SftNonhyeonApplication {
	public static void main(String[] args) {
		SpringApplication.run(SftNonhyeonApplication.class, args);
	}
}

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//public class SftNonhyeonApplication extends SpringBootServletInitializer {
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//
//		return builder.sources(new Class[] { SftNonhyeonApplication.class });
//	}
//}
