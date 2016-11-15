package com.newbee.poi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication  //等价于@ComponentScan+@EnableAutoConfiguration+@Configuration
@MapperScan("com.newbee.poi.*")
public class PoiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiDemoApplication.class, args);
	}
}
