package com.alejandro.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alejandro.webflux.client.ProductClient;
import com.alejandro.webflux.model.Product;

@SpringBootApplication
public class SpringWebfluxClientApplication implements CommandLineRunner {
	
	@Autowired
	private ProductClient client;

	private static final Logger log = LoggerFactory.getLogger(SpringWebfluxClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String id = this.client.findAll().blockFirst().getId();
		this.client.findAll().subscribe(p -> log.info(p.toString()));
		this.client.findById(id).subscribe(p -> log.info(p.toString()));
		Product product = this.client.findById(id).block();
		this.client.update(new Product("Candy man", 78.99), product.getId()).subscribe();
		this.client.findById(id).subscribe(p -> log.info(p.getName()));
		this.client.delete(product.getId());
		this.client.save(new Product("New", 55.55)).subscribe();
	}

}
