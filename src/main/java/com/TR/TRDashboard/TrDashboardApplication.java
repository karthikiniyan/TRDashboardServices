package com.TR.TRDashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.TR")
@EntityScan("com.TR.*")  
public class TrDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrDashboardApplication.class, args);
	}

	
}
