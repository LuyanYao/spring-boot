package com.demo.springboot_quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luyan on 2017/4/16.
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
	}
}
