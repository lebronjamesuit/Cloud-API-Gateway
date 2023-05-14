package com.jamesclear.first.microservice.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguation {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
							  
		 return	builder.routes()
				.route(p -> p
							.path("/get")
							.filters(
									f -> f.addRequestHeader("MyHeader", "added header")
								     	  .addRequestParameter("RequestParamMinh", "MyParamMinh"))
							.uri("http://httpbin.org:80"))
				
				.route(p -> p.path("/currency-exchange/**")
						  	.uri("lb://currency-exchange-service"))
				
				.route(p -> p.path("/currency-conversion/**")
							.uri("lb://currency-conversion-service"))
				
				.route(p -> p.path("/feign/currency-conversion/**")
					  		.uri("lb://currency-conversion-service"))
				
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 
								"/feign/currency-conversion/${segment}"))
						.uri("lb://currency-conversion-service"))
			
				.build();
							
				      
						          
						
					   			
		//return null;	 
	}
}
