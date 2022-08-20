package com.stackroute.capstone.apigateway.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.stackroute.capstone.apigateway.congif.JwtConfig;
import com.stackroute.capstone.apigateway.dto.ErrorResponseDto;
import com.stackroute.capstone.apigateway.util.JwtTokenUtil;
import com.stackroute.capstone.apigateway.util.RouterValidator;

import reactor.core.publisher.Flux;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private  RouterValidator routerValidator;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtConfig jwtConfig;
	
	public AuthenticationFilter() {
		super(Config.class);
	}



	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			System.out.println("##1## route : "+routerValidator.isSecured.test(exchange.getRequest()));
			if (routerValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Authorisation Header");
				}

				String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
				try {
					jwtTokenUtil.validateToken(authHeader);
				}
				catch (Exception ex) {
					List<String> details = new ArrayList<>();
					details.add(ex.getLocalizedMessage());
					ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, exchange.getRequest().getURI().toString());
					ServerHttpResponse response = exchange.getResponse();

					byte[] bytes = SerializationUtils.serialize(error);

					DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
					response.writeWith(Flux.just(buffer));
					response.setStatusCode(HttpStatus.UNAUTHORIZED);
					return response.setComplete();
				}
			}

			return chain.filter(exchange);
		});
	}

	public static class Config {
		private String baseMessage;
		private boolean preLogger;
		private boolean postLogger;
		
public Config() {
	// TODO Auto-generated constructor stub
}


public Config(String baseMessage, boolean preLogger, boolean postLogger) {
	super();
	this.baseMessage = baseMessage;
	this.preLogger = preLogger;
	this.postLogger = postLogger;
}


public String getBaseMessage() {
	return baseMessage;
}

public void setBaseMessage(String baseMessage) {
	this.baseMessage = baseMessage;
}

public boolean isPreLogger() {
	return preLogger;
}

public void setPreLogger(boolean preLogger) {
	this.preLogger = preLogger;
}

public boolean isPostLogger() {
	return postLogger;
}

public void setPostLogger(boolean postLogger) {
	this.postLogger = postLogger;
}


		
	}
}