package com.ag.energyconsumption.model.repository;

import java.math.BigDecimal;

import com.ag.energyconsumption.model.EnergyConsumptionData;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "EnergyConsumption")
@NoArgsConstructor
public class EnergyConsumptionRepositoryData extends EnergyConsumptionData {
	
	public EnergyConsumptionRepositoryData(EnergyConsumptionData energyConsumptionData) {
		this.product = energyConsumptionData.getProduct();
		this.api = energyConsumptionData.getApi();
		this.fullName = energyConsumptionData.getFullName();
		this.key = energyConsumptionData.getKey();
		this.units = energyConsumptionData.getUnits();
		this.quantity2019 = energyConsumptionData.getQuantity2019();
		
	}
	
	public EnergyConsumptionData toEnergyConsumptionData() {
		return new EnergyConsumptionData(
				product, fullName, api, key, units, quantity2019);
		
	}
	
	
    @DynamoDBHashKey
    public String getProduct() {
        return product;
    }
    
    @DynamoDBAttribute
	public String getFullName() {
		return fullName;
	}
    
    @DynamoDBAttribute
	public String getApi() {
		return api;
	}
    
    @DynamoDBAttribute
	public String getKey() {
		return key;
	}
    
    @DynamoDBAttribute
	public String getUnits() {
		return units;
	}
    
    @DynamoDBAttribute
	public BigDecimal getQuantity2019() {
		return quantity2019;
	}
    
	
}
