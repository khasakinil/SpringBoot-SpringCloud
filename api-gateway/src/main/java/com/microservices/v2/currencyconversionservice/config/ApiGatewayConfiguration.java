package com.microservices.v2.currencyconversionservice.config;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		return builder.routes().route(p -> p.path("/get")
				.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("MyParam", "ParamValue"))
				.uri("http://httpbin.org:80"))
				.route(p -> p.path("/api/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p -> p.path("/api/currency-conversion/**").uri("lb://currency-conversion")).build();
	}
}
