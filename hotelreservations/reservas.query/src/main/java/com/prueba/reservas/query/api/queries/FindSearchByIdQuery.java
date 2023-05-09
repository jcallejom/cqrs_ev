package com.prueba.reservas.query.api.queries;

import com.prueba.cqrs.core.queries.BaseQuery;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindSearchByIdQuery extends BaseQuery {
	private String id;

}
