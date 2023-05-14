package com.jamesclear.first.microservice.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {
	
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
		
		logger.info(exchange.getClass().toString());
		logger.info("this is a path ", exchange.getRequest().getPath().toString());
		
		justWonderingMono();
		justWonderFlux();
		
		return chain.filter(exchange);
	}
	
	public void justWonderingMono() {

		//
		 Mono<Integer> monoStream = Mono.just(10);
         
	        monoStream.subscribe(val ->
	          {
	        	  // Everytime we call subscribe we can react differently 
	            System.out.println("Subscriber uses the values from publisher: "+val*10);
	             
	          });
	         
	        monoStream.subscribe(val ->
	          {
	            System.out.println("Subscriber uses the values from publisher: "+val*-10);
	             
	          });
		//
		
	}
	
	public void justWonderFlux() {
		Flux.range(1, 5).subscribe(
				  successValue -> System.out.println(successValue),
				  error -> System.err.println(error.getMessage()),
				  () -> System.out.println("Flux consumed.")
				);
		
		
	}


}
