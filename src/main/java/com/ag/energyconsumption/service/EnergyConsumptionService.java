package com.ag.services.service;


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ag.services.model.EnergyConsumptionData;

@Service
public class EnergyConsumptionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnergyConsumptionService.class);
	

	public EnergyConsumptionData getEnergyConsumtptionData(String product) {
		
		LOGGER.info("Received getEnergyConsumtptionData request for [{}]", product);
		
		EnergyConsumptionData energyConsumptionData =  new EnergyConsumptionData(
					product, 
					product.toUpperCase(),
					"api__", 
					"key__",
					"MTT", 
					new BigDecimal(200.15)
					);
		
		LOGGER.info("Sending : [{}]", energyConsumptionData.toString());
		
		return energyConsumptionData;
	}
	
}
