package com.example.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        //feign- problem 1
        try {

            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("from", from);
            uriVariables.put("to", to);
            //ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8100/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
            ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity("http://localhost:8100/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);

            CurrencyConversionBean responseEntityBody = responseEntity.getBody();

            return new CurrencyConversionBean(responseEntityBody.getId(), from, to, responseEntityBody.getConversionMultiple(), quantity, quantity.multiply(responseEntityBody.getConversionMultiple()), responseEntityBody.getPort());

        } catch (RuntimeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }

    }


    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
// using feign we did above task using two lines only by creating proxy
        CurrencyConversionBean responseEntityBody = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversionBean(responseEntityBody.getId(), from, to, responseEntityBody.getConversionMultiple(), quantity, quantity.multiply(responseEntityBody.getConversionMultiple()), responseEntityBody.getPort());
    }

}
