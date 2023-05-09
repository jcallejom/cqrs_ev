package com.prueba.reservas.query;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prueba.cqrs.core.infrastructure.QueryDispatcher;
import com.prueba.reservas.query.api.queries.FindSearchByIdQuery;
import com.prueba.reservas.query.api.queries.QueryHandler;

@SpringBootApplication
public class QueryApplication {
	
	@Autowired
	private QueryDispatcher queryDispatcher;
	@Autowired
	private QueryHandler queryHandler;
	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}
	@PostConstruct
	public void registrer() { 
		queryDispatcher.registerHandler(FindSearchByIdQuery.class, queryHandler::handle);
	}
}
