package com.microservices.v2.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.v2.currencyconversionservice.bean.CurrencyConversion;

@RestController
@RequestMapping("/api/currency-conversion")
public class CurrencyConversionController {

	@RequestMapping(path = "/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
	public CurrencyConversion calculateCurrencyConversion(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {
		return new CurrencyConversion(10001L, from, to, quantity, BigDecimal.ONE, BigDecimal.ONE, "");
	}
}
