package com.microservices.v2.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.v2.currencyconversionservice.bean.CurrencyConversion;

@RestController
@RequestMapping("/api/currency-conversion")
public class CurrencyConversionController {

	@RequestMapping(path = "/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
	public CurrencyConversion calculateCurrencyConversion(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity) {

		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/api/currency-exchange/from/{from}/to/{to}/", CurrencyConversion.class,
				uriVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));

		return currencyConversion;
	}
}
