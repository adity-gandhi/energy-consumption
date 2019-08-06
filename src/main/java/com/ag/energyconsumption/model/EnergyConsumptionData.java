package com.ag.energyconsumption.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EnergyConsumptionData {
	
	protected String product;
	protected String fullName;
	protected String api;
	protected String key;
	protected String units;
	protected BigDecimal quantity2019;

}
