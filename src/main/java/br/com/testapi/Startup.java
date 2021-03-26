package br.com.testapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

/* @EnableAutoConfiguration 
 * allows that spring context application be automatically loaded 
 * based on JAR's and configuration defined by the developer.
 * */
@EnableAutoConfiguration

/* @ComponentScan
 * says to springboot must scan your packages and find configuration files.
 * */
@ComponentScan
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}
