package com.ag.energyconsumption.repository;

import java.util.List;

import com.ag.energyconsumption.model.EnergyConsumptionData;

import io.micrometer.core.annotation.Timed;

public interface EnergyConsumptionRepository {
	
	public EnergyConsumptionData findById(String uuid);
	
	public List<EnergyConsumptionData> findAll();
	
	public EnergyConsumptionData findByProduct(String product);
	
	@Timed
	public  EnergyConsumptionData createEnergyConsumption(EnergyConsumptionData energyConsumptionData);

	@Timed
	public EnergyConsumptionData updateEnergyConsumtptionData(String product,
			EnergyConsumptionData energyConsumptionData);

	@Timed
	public boolean deleteEnergyConsumptionData(String product, EnergyConsumptionData energyConsumptionData);

}
