package com.study.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.study.*"}) 
public class SpringBbsApplication {
  //같은 패키지 내에서는 IoC 를 해준다 객체생성
	public static void main(String[] args) {
		SpringApplication.run(SpringBbsApplication.class, args);
	} 

}
