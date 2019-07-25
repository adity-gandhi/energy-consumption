package com.ag.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ag.services.model.EnergyConsumptionData;
import com.ag.services.service.EnergyConsumptionService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@Timed("EnergyConsumption")
public class EnergyConsumptionController {

	@Autowired
	private EnergyConsumptionService energyConsumptionService;

	@RequestMapping(method = RequestMethod.GET, value = "/energyConsumption")
	@ApiOperation(value = "Returns EnergyConsumption Data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success. EnergyConsumption Data is retrieved"),
							@ApiResponse(code = 500, message = "Internal server error") })
	public  EnergyConsumptionData getEnergyConsumption( @ApiParam("product for which to get EnergyConsumption data")
							@RequestParam(value="product", required=true) String product) {
		return energyConsumptionService.getEnergyConsumtptionData(product);
	}

}