package com.ag.services.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EnergyConsumptionData {
	
	private String product;
	private String fullName;
	private String api;
	private String key;
	private String units;
	private BigDecimal quantity2019;

}
