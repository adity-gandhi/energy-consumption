package com.ag.energyconsumption.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ag.energyconsumption.model.EnergyConsumptionData;
import com.ag.energyconsumption.service.EnergyConsumptionService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
public class EnergyConsumptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnergyConsumptionController.class);
	
	@Autowired
	private EnergyConsumptionService energyConsumptionService;

	@Timed	
	@RequestMapping(method = RequestMethod.GET, value = "/energyConsumption")
	@ApiOperation(value = "Returns EnergyConsumption Data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success. EnergyConsumption Data fetched"),
							@ApiResponse(code = 500, message = "Internal server error") })
	public  List<EnergyConsumptionData> getAllEnergyConsumption() {
		
		LOGGER.info("in EnergyConsumptionController::getEnergyConsumption");
				
		return energyConsumptionService.getAllEnergyConsumptionData();
	}
	
	@Timed	
	@RequestMapping(method = RequestMethod.GET, value = "/energyConsumption/{product}")
	@ApiOperation(value = "Returns EnergyConsumption Data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success. EnergyConsumption Data fetched for a product"),
							@ApiResponse(code = 500, message = "Internal server error") })
	public  EnergyConsumptionData getEnergyConsumption( @ApiParam("product for which to get EnergyConsumption data")
							@RequestParam(value="product", required=true) String product) {
		
		LOGGER.info("in EnergyConsumptionController::getEnergyConsumption");
		CompositeMeterRegistry registry = Metrics.globalRegistry;
		for (MeterRegistry iRegistry : registry.getRegistries()) {
			LOGGER.info("Registry " + iRegistry.toString());
		
			for (Meter iMeter :registry.getMeters()) {
				LOGGER.info("Meter: " + iMeter.toString());
			}
		}
		
		return energyConsumptionService.getEnergyConsumptionData(product);
	}

	@Timed	
	@RequestMapping(method = RequestMethod.POST, value = "/energyConsumption")
	@ApiOperation(value = "Creates EnergyConsumption Data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success. EnergyConsumption Data created"),
							@ApiResponse(code = 500, message = "Internal server error") })
	public  EnergyConsumptionData createEnergyConsumption( @RequestBody EnergyConsumptionData energyConsumptionData) {
		
		LOGGER.info("in EnergyConsumptionController::createEnergyConsumption [{}]", energyConsumptionData);
		
		return energyConsumptionService.createEnergyConsumption(energyConsumptionData);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/energyConsumption/{product}")
	public EnergyConsumptionData updateEnergyConsumption(
			@ApiParam("product for which to get EnergyConsumption data") 
				@RequestParam(value="product", required=true) String product,			
			@RequestBody EnergyConsumptionData energyConsumptionData) {

		LOGGER.info("in EnergyConsumptionController::updateEnergyConsumption");
				
		return energyConsumptionService.updateEnergyConsumption(product, energyConsumptionData);

	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/energyConsumption/{product}")
	public boolean deleteEnergyConsumption(
			@ApiParam("delete EnergyConsumption data by product") 
				@RequestParam(value="product", required=true) String product,			
			@RequestBody EnergyConsumptionData energyConsumptionData) {

		LOGGER.info("in deleteEnergyConsumption");
				
		return energyConsumptionService.deleteEnergyConsumption(product, energyConsumptionData);

	}
	   

}