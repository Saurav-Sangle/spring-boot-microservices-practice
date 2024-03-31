package com.springcloud.microservices.limitsservice.LimitsController;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.microservices.limitsservice.Configuration;
import com.springcloud.microservices.limitsservice.beans.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {
    @Autowired
    private Configuration configuration;

/*

    @GetMapping(path = "/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(1000, 10);
    }
*/

    @GetMapping(path = "/limits-configProp")
    public LimitConfiguration retrieveLimitsFromConfigurationProperties() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    /*@GetMapping(path = "/fault-tolerance")
    @HystrixCommand(fallbackMethod = "faultToleranceConfigurationMethod",defaultFallback = "faultToleranceConfigurationMethod")
    public LimitConfiguration faultToleranceConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitConfiguration faultToleranceConfigurationMethod() {
        return new LimitConfiguration(9999, 99);
    }
*/
}
