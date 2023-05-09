package com.prueba.reservas.cmd.api.command;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.prueba.cqrs.core.commands.BaseCommand;

import lombok.Data;

//@Validated
public final class SaveSearchCommand extends BaseCommand{
	
	@NotBlank
	@NotNull(message = "hotelId cannot be null")
	private final String hotelId;
	@NotBlank
	@NotNull(message = "checkIn cannot be null")
	private final String checkIn;
	@NotBlank
	@NotNull(message = "checkOut cannot be null")
	private final String checkOut;
	@NotEmpty (message = "ages cannot be empy")
	private final List<Integer> ages;
//	private Date checkIn;
//	private Date checkOut;
	

	public SaveSearchCommand(String id, String hotelId,
			 String checkIn,
			 String checkOut,
			List<Integer> ages) {
		super(id);
		this.hotelId = hotelId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.ages = ages;
	}
	
	public String getHotelId() {
		return hotelId;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public List<Integer> getAges() {
		//return immutable list
		return Collections.unmodifiableList(this.ages);
	}
}
