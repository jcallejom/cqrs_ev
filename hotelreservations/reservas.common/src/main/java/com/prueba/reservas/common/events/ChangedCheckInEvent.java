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
public final class ChangedCheckInEvent extends BaseEvent{
	
	private Date checkIn;
//	private Date checkIn;
	



	

}
