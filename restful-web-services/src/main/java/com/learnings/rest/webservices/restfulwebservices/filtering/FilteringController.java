package com.learnings.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public SomeBean filtering() {
        return new SomeBean("Val1", "Val2", "val2");
    }

    @GetMapping(path = "/filtering-list")
    public List<SomeBean> filteringList() {
        return Arrays.asList(new SomeBean("Val1", "Val2", "val2"), new SomeBean("Val4", "Val5", "val6"));
    }

    @GetMapping(path = "/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering() {
        SomeBean someBean = new SomeBean("Val1", "Val2", "val2");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
    @GetMapping(path = "/dynamic-filtering-list")
    public MappingJacksonValue dynamicFilteringList() {
        List<SomeBean> list=Arrays.asList(new SomeBean("Val1", "Val2", "val2"), new SomeBean("Val4", "Val5", "val6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filters=SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filter=new SimpleFilterProvider().addFilter("SomeBean",filters);
        mappingJacksonValue.setFilters(filter);
        return mappingJacksonValue;
    }

}
