package com.prueba.reservas.query.api.vo;

import java.util.List;

import com.prueba.reservas.common.dto.BaseResponse;
import com.prueba.reservas.query.domain.SearchsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SearchLookupResponse extends BaseResponse{
	private List<SearchsEntity> searchs;
	private long count;
	
	public SearchLookupResponse(String message) {
		super(message);
	}

	
}
