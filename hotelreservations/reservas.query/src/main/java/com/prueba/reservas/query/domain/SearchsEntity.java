package com.prueba.reservas.query.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.prueba.cqrs.core.domain.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)//esto indca q no aplique el por defecto que metamos los include a mano
@Entity
public class SearchsEntity extends BaseEntity{
	@Id
	private String id;
	@EqualsAndHashCode.Include
	private String hotelId;
	@EqualsAndHashCode.Include
	private Date checkIn;
	@EqualsAndHashCode.Include
	private Date checkOut;
	//private List ages;
	@EqualsAndHashCode.Include
	private String ages;
	private Date createdDate;
}
