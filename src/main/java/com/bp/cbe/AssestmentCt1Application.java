package com.bp.cbe;

import com.bp.cbe.config.CourseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({CourseProperties.class})
@EnableFeignClients
public class AssestmentCt1Application {

	public static void main(String[] args) {
		SpringApplication.run(AssestmentCt1Application.class, args);
	}

}
