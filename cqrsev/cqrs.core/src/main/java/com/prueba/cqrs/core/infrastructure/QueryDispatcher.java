package com.prueba.cqrs.core.infrastructure;

import java.util.List;

import com.prueba.cqrs.core.domain.BaseEntity;
import com.prueba.cqrs.core.queries.BaseQuery;
import com.prueba.cqrs.core.queries.QueryHandlerMethod;

public interface QueryDispatcher {
	<T extends BaseQuery> void registerHandler(Class <T> type, QueryHandlerMethod<T> handler);
	<U extends BaseEntity> List<U> send(BaseQuery query);
}
