package com.prueba.reservas.query.api.controllers;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cqrs.core.infrastructure.QueryDispatcher;
import com.prueba.reservas.query.api.queries.FindSearchByIdQuery;
import com.prueba.reservas.query.api.vo.SearchLookupResponse;
import com.prueba.reservas.query.domain.SearchsEntity;

@RestController
@RequestMapping(path = "/")
public class SearchLookupController {
	private final Logger logger = Logger.getLogger(SearchLookupController.class.getName());
	@Autowired
	private QueryDispatcher queryDispatcher;


	@GetMapping(path = "/count/{id}")
	public ResponseEntity<SearchLookupResponse> getAccountsById(@PathVariable(value = "id") String id) {
		try {
			List<SearchsEntity> searchs = queryDispatcher.send(new FindSearchByIdQuery(id));
			if (searchs == null || searchs.size() == 0) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			var response = SearchLookupResponse.builder().searchs(searchs)
					.count(searchs.size())
					.message(MessageFormat.format("The query was successful", null)).build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			var safeErrorMessage = "Error executing query";
			logger.log(Level.SEVERE,safeErrorMessage,e);

			return new ResponseEntity<>(new SearchLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
}
