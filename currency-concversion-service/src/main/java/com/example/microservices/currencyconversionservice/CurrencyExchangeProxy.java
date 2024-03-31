package com.example.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service",url="http://localhost:8100")
//Above is not feasible solution if multiple instances came in picture we need configure all urls in above annotation so lets se eureka
@FeignClient(name="currency-exchange-service")
//@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {
    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
