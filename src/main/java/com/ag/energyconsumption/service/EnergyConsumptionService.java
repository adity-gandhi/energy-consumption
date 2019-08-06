package com.ag.energyconsumption.service;


import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ag.energyconsumption.EnergyConsumptionConfiguration;
import com.ag.energyconsumption.model.EnergyConsumptionData;
import com.ag.energyconsumption.repository.EnergyConsumptionRepository;

import io.micrometer.core.annotation.Timed;


@Service
public class EnergyConsumptionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnergyConsumptionService.class);
	
	private final EnergyConsumptionRepository energyConsumptionRepository;
	private final EnergyConsumptionConfiguration energyConsumptionConfiguration;
	
	@Autowired
	public EnergyConsumptionService(EnergyConsumptionRepository energyConsumptionRepository, 
				EnergyConsumptionConfiguration energyConsumptionConfiguration) {
		this.energyConsumptionRepository = energyConsumptionRepository;
		this.energyConsumptionConfiguration = energyConsumptionConfiguration;
	}
	
	@Timed
	public EnergyConsumptionData getEnergyConsumptionData(String product) {
				
		LOGGER.info("Received getEnergyConsumtptionData request for [{}]", product);
		
		
		EnergyConsumptionData energyConsumptionData = energyConsumptionRepository.findByProduct(product);
		
		testForTiming();
		
		LOGGER.info("Sending : [{}]", energyConsumptionData);
		
		return energyConsumptionData;
	}

	@Timed
	public void testForTiming() {
		
		int i = 0;
		while(i<1000) {
			i = i +1;
			
		}
		
	}
	
	public  EnergyConsumptionData createEnergyConsumption(EnergyConsumptionData energyConsumptionData){
		LOGGER.trace("createEnergyConsumption(EnergyConsumptionData energyConsumptionData)");
		return energyConsumptionRepository.createEnergyConsumption(energyConsumptionData);
		
	}

	public EnergyConsumptionData updateEnergyConsumption(String product,
			EnergyConsumptionData energyConsumptionData) {

		return energyConsumptionRepository.updateEnergyConsumtptionData(product, energyConsumptionData);
	}

	public List<EnergyConsumptionData> getAllEnergyConsumptionData() {

		return energyConsumptionRepository.findAll();
	}

	public boolean deleteEnergyConsumption(String product, EnergyConsumptionData energyConsumptionData) {

		return energyConsumptionRepository.deleteEnergyConsumptionData(product, energyConsumptionData);
	}
	
}
