package com.prueba.reservas.cmd.api.command;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.prueba.cqrs.core.commands.BaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SaveSearchCommand", description = "command represent a save search on database")
public final class SaveSearchCommand extends BaseCommand{
    @Schema(name = "hotelId", requiredMode = Schema.RequiredMode.REQUIRED,example = "01abct", defaultValue = "01abct", description = "Unique id of hotel that represent the owner of hotel")
	@NotBlank
	@NotNull(message = "hotelId cannot be null")
	private final String hotelId;
    @Schema(name = "checkIn", requiredMode = Schema.RequiredMode.REQUIRED,example = "25/10/2013", defaultValue = "25/10/2013", description = "checkin date")
    @NotBlank
	@NotNull(message = "checkIn cannot be null")
	private final String checkIn;
    @Schema(name = "checkIn", requiredMode = Schema.RequiredMode.REQUIRED,example = "25/10/2033", defaultValue = "25/10/2033", description = "checkout date")
    @NotBlank
	@NotNull(message = "checkOut cannot be null")
	private final String checkOut;
    @Schema(name = "products", requiredMode = Schema.RequiredMode.REQUIRED, description = "list of ages of people")
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
