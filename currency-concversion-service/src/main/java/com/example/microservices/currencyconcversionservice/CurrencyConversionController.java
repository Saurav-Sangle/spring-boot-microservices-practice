package com.example.microservices.currencyconcversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

   /* @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
//feign- problem 1
        Map<String, String> uriVariables=new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurrencyConversionBean> responseEntity=new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",CurrencyConversionBean.class,uriVariables);
        CurrencyConversionBean responseEntityBody=responseEntity.getBody();
        return new CurrencyConversionBean(responseEntityBody.getId(), from, to,responseEntityBody.getConversionMultiple(),quantity, quantity.multiply(responseEntityBody.getConversionMultiple()), responseEntityBody.getPort());
    }
*/
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
//feign
       CurrencyConversionBean responseEntityBody=proxy.retrieveExchangeValue(from,to);
        return new CurrencyConversionBean(responseEntityBody.getId(), from, to,responseEntityBody.getConversionMultiple(),quantity, quantity.multiply(responseEntityBody.getConversionMultiple()), responseEntityBody.getPort());
    }

}
