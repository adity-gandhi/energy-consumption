package com.ag.energyconsumption.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ag.energyconsumption.EnergyConsumptionConfiguration;
import com.ag.energyconsumption.controller.EnergyConsumptionController;
import com.ag.energyconsumption.model.EnergyConsumptionData;
import com.ag.energyconsumption.repository.EnergyConsumptionRepository;
import com.ag.energyconsumption.repository.dynamodb.DynamoDBConfiguration;
import com.ag.energyconsumption.repository.dynamodb.EnergyConsumptionDynamoDBRepository;
import com.ag.framework.monitoring.EnableMonitoring;
import com.ag.framework.monitoring.MonitoringConfig;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		DynamoDBConfiguration.class, 
		EnergyConsumptionService.class, 
		EnergyConsumptionDynamoDBRepository.class, 
		EnergyConsumptionConfiguration.class,
		MonitoringConfig.class}) 
@SpringBootTest
@Import({MetricsAutoConfiguration.class, CompositeMeterRegistryAutoConfiguration.class})
public class ApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	AmazonDynamoDBClient amazonDynamoDBClientLocal;
	
	@Autowired
	AmazonDynamoDB amazonDynamoDB;
	
	@Autowired
	EnergyConsumptionService energyConsumptionService;
	
	@Test
	public void contextLoads() {
	}
	
	//@Test
	public void testLocalDynamoDBConnection() {

		for (String tableName : amazonDynamoDBClientLocal.listTables().getTableNames()) {
			LOGGER.debug("tableName: " + tableName);	
		}
		
		assertTrue("table myTable exists", 
				amazonDynamoDBClientLocal.listTables().getTableNames().contains("EnergyConsumption"));
		
		
		DescribeTableResult dtr = amazonDynamoDBClientLocal.describeTable("myTable");
		System.out.print(dtr);
	}

	//@Test
	public void testDynamoDBConnection() {

		for (String tableName : amazonDynamoDB.listTables().getTableNames()) {
			LOGGER.debug("tableName: " + tableName);	
		}
		
		assertTrue("table energy consumtion exists", 
				amazonDynamoDB.listTables().getTableNames().contains("EnergyConsumption"));
		
		
	}
	
	@Test
	public void test_CreateEnergyConsumption_simpleSuccess() {
		EnergyConsumptionData energyConsumptionData = 
					new EnergyConsumptionData("product","fullName", "api", "key", "units", new BigDecimal(1));
		energyConsumptionService.createEnergyConsumption(energyConsumptionData);
		
		EnergyConsumptionData energyConsumptionData2 =
				energyConsumptionService.getEnergyConsumptionData("product");
		assertEquals(energyConsumptionData, energyConsumptionData2);
		
	}

	@Test
	public void test_UpdateEnergyConsumption_simpleSuccess() {
		EnergyConsumptionData energyConsumptionData = 
					new EnergyConsumptionData("product","fullName", "api", "key", "units", new BigDecimal(1));
		energyConsumptionService.createEnergyConsumption(energyConsumptionData);
		energyConsumptionData.setApi("api2");
		energyConsumptionData.setKey("key2");
		energyConsumptionService.updateEnergyConsumption("product", energyConsumptionData);

		EnergyConsumptionData energyConsumptionData2 =energyConsumptionService.getEnergyConsumptionData("product");
		assertEquals(energyConsumptionData.getApi(), "api2");
		assertEquals(energyConsumptionData.getKey(), "key2");

		
	}
	
		
	@Test
	public void getAllEnergyConsumptionData() {
		List<EnergyConsumptionData> list = energyConsumptionService.getAllEnergyConsumptionData();
		
		assertNotNull(list);
		LOGGER.info("list size [{}]" , list.size());

		
	}
	
	@Test
	public void test_DeleteEnergyConsumption_simpleSuccess() {
		EnergyConsumptionData energyConsumptionData = 
					new EnergyConsumptionData("product_delete","fullName_delete", "api_delete", "key_delete", "units_delete", new BigDecimal(1));
		energyConsumptionService.createEnergyConsumption(energyConsumptionData);
		
		EnergyConsumptionData energyConsumptionData2 =
				energyConsumptionService.getEnergyConsumptionData("product_delete");
		assertEquals(energyConsumptionData, energyConsumptionData2);
		
		energyConsumptionService.deleteEnergyConsumption("product_delete", energyConsumptionData);
		
		assert(null==energyConsumptionService.getEnergyConsumptionData("product_delete"));
		
		
	}
	
	@Test
	public void test_GetEnergyConsumption_doesNotExist() {
		

		EnergyConsumptionData energyConsumptionData =
				energyConsumptionService.getEnergyConsumptionData("product_doesNotExist_12345");
		assert(null==energyConsumptionData);
		
		
	}

	
}
