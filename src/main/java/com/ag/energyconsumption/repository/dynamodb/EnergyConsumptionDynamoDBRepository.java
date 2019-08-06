package com.ag.energyconsumption.repository.dynamodb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ag.energyconsumption.model.EnergyConsumptionData;
import com.ag.energyconsumption.model.repository.EnergyConsumptionRepositoryData;
import com.ag.energyconsumption.repository.EnergyConsumptionRepository;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

import io.micrometer.core.annotation.Timed;

@Component
public class EnergyConsumptionDynamoDBRepository implements EnergyConsumptionRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(EnergyConsumptionDynamoDBRepository.class);
	//todo: create a slf4jlog class annotation for this like the lombok log anotaion 
	
	private AmazonDynamoDB amazonDynamoDB;
	private DynamoDBMapper dynamoDBMapper;
	@Autowired
	public EnergyConsumptionDynamoDBRepository(AmazonDynamoDB amazonDynamoDB){
		this.amazonDynamoDB = amazonDynamoDB;
		this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
	
	}
	
	@Override
	public EnergyConsumptionData findById(String uuid) {
		return findByProduct(uuid);
	}

	@Override
	public List<EnergyConsumptionData> findAll() {
		LOGGER.trace("findAll");
		PaginatedScanList<EnergyConsumptionRepositoryData> paginatedScanList = 
					dynamoDBMapper.scan(EnergyConsumptionRepositoryData.class, new DynamoDBScanExpression());
		
		paginatedScanList.loadAllResults();
	    List<EnergyConsumptionData> list = new ArrayList<EnergyConsumptionData>(paginatedScanList.size());
	    for(EnergyConsumptionData energyConsumptionData: paginatedScanList) {
	        list.add(energyConsumptionData);
	    }
	    return list;
	}

	@Override
	public EnergyConsumptionData findByProduct(String product) {
		LOGGER.trace("findByProduct");
		EnergyConsumptionRepositoryData energyConsumptionRepositoryData = 
					dynamoDBMapper.load(EnergyConsumptionRepositoryData.class, product);
		EnergyConsumptionData energyConsumptionData = null;
		if(null != energyConsumptionRepositoryData) {			
			energyConsumptionData = energyConsumptionRepositoryData.toEnergyConsumptionData();
		}
		return energyConsumptionData;
	}

	@Override
	@Timed
	public EnergyConsumptionData createEnergyConsumption(EnergyConsumptionData energyConsumptionData) {
		
		LOGGER.trace("createEnergyConsumption");
		
		EnergyConsumptionRepositoryData energyConsumptionRepositoryData = 
				new EnergyConsumptionRepositoryData(energyConsumptionData);
		
		dynamoDBMapper.save(energyConsumptionRepositoryData);
		
		return energyConsumptionRepositoryData.toEnergyConsumptionData();
		
	}
	
	@Override
	@Timed
	public EnergyConsumptionData updateEnergyConsumtptionData(String product,
			EnergyConsumptionData energyConsumptionData) {
		
		LOGGER.trace("createEnergyConsumption");
		
		EnergyConsumptionRepositoryData energyConsumptionRepositoryData = 
				new EnergyConsumptionRepositoryData(energyConsumptionData);
		
		dynamoDBMapper.save(energyConsumptionRepositoryData);
		
		return energyConsumptionRepositoryData.toEnergyConsumptionData();
	}
	
	@Override
	@Timed
	public boolean deleteEnergyConsumptionData(String product,
			EnergyConsumptionData energyConsumptionData) {
		
		LOGGER.trace("deleteEnergyConsumtptionData");
		
		EnergyConsumptionRepositoryData energyConsumptionRepositoryData = 
				new EnergyConsumptionRepositoryData(energyConsumptionData);
		
		dynamoDBMapper.delete(energyConsumptionRepositoryData);
		
		return true;
	}
	
	
}
