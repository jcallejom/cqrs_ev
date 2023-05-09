package com.prueba.reservas.query.api.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.domain.BaseEntity;
import com.prueba.reservas.query.domain.SearchRepository;
@Service
public class SearchQueryHandler implements QueryHandler{
	
	@Autowired
	private SearchRepository repository;
	
	@Override
	public List<BaseEntity> handle(FindSearchByIdQuery query) {
		var search= repository.findById(query.getId());
		if(search.isEmpty()){	
			return null;
		}
		List<BaseEntity> searchsList=new ArrayList();
		searchsList.add(search.get());
		return searchsList;
	}

}
