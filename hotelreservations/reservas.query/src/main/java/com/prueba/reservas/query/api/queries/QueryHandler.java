package com.prueba.reservas.query.api.queries;

import java.util.List;

import com.prueba.cqrs.core.domain.BaseEntity;

public interface QueryHandler {
	List<BaseEntity> handle (FindSearchByIdQuery query);
}
