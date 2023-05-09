package com.prueba.reservas.common.events;

import java.util.Date;

import com.prueba.cqrs.core.events.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SearchSavedEvent extends BaseEvent{
	private String hotelId;
	private Date checkIn;
	private Date checkOut;
	//private List ages;
	private String ages;
	private Date createdDate;
}
