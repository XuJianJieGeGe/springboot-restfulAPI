package com.xjj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger使用的步骤：
 *    1、加入依赖
 *    2、@EnableSwagger2
 *    3、http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
