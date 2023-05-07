package com.microservices.v2.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.v2.limitsservice.bean.Limits;
import com.microservices.v2.limitsservice.configuration.Configuration;

@RestController
@RequestMapping("/api")
public class LimitsController {

	@Autowired
	private Configuration config;

	@RequestMapping(method = RequestMethod.GET, path = "/limits")
	public Limits userMessage() {
		return new Limits(config.getMinimum(), config.getMaximum());
	}

}
