package com.alejandro.webflux.client;

import com.alejandro.webflux.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductClient {
	
	public Flux<Product> findAll();
	
	public Mono<Product> findById(String id);
	
	public Mono<Product> save(Product product);
	
	public Mono<Product> update(Product product, String id);
	
	public Mono<Void> delete(String id);

}
