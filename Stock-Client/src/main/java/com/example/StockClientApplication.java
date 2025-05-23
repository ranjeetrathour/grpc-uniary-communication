package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import service.StockClientService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example", "service"}) // Add "service"
public class StockClientApplication implements CommandLineRunner {
	@Autowired
	private StockClientService stockClientService;

	public static void main(String[] args) {
		SpringApplication.run(StockClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Client call to gRPC server: " + stockClientService.getStockPrice("tata"));
	}
}

