package com.alejandro.webflux.client;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.alejandro.webflux.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductClientImpl implements ProductClient {
	
	@Autowired
	private WebClient client;

	public static final String PATH_VARIABLE_ID = "localhost:8090/api/v2/product/{id}";
	public static final String KEY_ID = "id";
	
	@Override
	public Flux<Product> findAll() {
		return this.client
				.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Product.class);
	}

	@Override
	public Mono<Product> findById(String id) {
		return this.client
				.get()
				.uri(PATH_VARIABLE_ID, Collections.singletonMap(KEY_ID, id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Product.class);
	}

	@Override
	public Mono<Product> save(Product product) {
		return this.client
				.post()
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(product)
				.retrieve()
				.bodyToMono(Product.class);
	}

	@Override
	public Mono<Product> update(Product product, String id) {
		return this.client
				.put()
				.uri(PATH_VARIABLE_ID, Collections.singletonMap(KEY_ID, id))
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(product)
				.retrieve()
				.bodyToMono(Product.class);
	}

	@Override
	public Mono<Void> delete(String id) {
		 return this.client
				.delete()
				.uri(PATH_VARIABLE_ID, Collections.singletonMap(KEY_ID, id))
				.retrieve()
				.bodyToMono(Void.class);
		
	}

}
