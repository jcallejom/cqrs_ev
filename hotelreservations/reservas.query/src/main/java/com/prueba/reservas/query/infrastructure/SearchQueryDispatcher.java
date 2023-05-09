package com.prueba.reservas.query.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.domain.BaseEntity;
import com.prueba.cqrs.core.infrastructure.QueryDispatcher;
import com.prueba.cqrs.core.queries.BaseQuery;
import com.prueba.cqrs.core.queries.QueryHandlerMethod;

@Service
public class SearchQueryDispatcher implements QueryDispatcher {
	
	private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>>routes= new HashMap<>();
	@Override
	public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
		var handlers=routes.computeIfAbsent(type, c-> new LinkedList<>());
		handlers.add(handler);
		
	}

	@Override
	public <U extends BaseEntity> List<U> send(BaseQuery query) {
		// TODO Auto-generated method stub
		//obtengo su unico handler 
		var handlers=routes.get(query.getClass());
		if(handlers==null || handlers.size()==0)
			throw new RuntimeException("The query handler was not registered");
		if (handlers.size()>1)
			throw new RuntimeException("You cannot send a query that has more handler");
		return handlers.get(0).handle(query);
	}

}
