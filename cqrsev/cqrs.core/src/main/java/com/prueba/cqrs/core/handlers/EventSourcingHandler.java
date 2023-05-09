package com.prueba.cqrs.core.handlers;

import com.prueba.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
	void save(AggregateRoot aggregate);
	T getById(String id);

}
