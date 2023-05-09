package com.prueba.reservas.cmd.api.vo;

import com.prueba.reservas.common.dto.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveSearchResponse extends BaseResponse{
	private String id;

	public SaveSearchResponse(String messagge,String id) {
		super(messagge);
		this.id = id;
	}

}
