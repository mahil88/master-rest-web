package com.example.rest.webservices.restfulwebservices.filtering;

import com.example.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping( path = "/filtering")
    public MappingJacksonValue filtering(){
        FilterBean bean =new FilterBean("value1","value2","value3");
        SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider provider = new SimpleFilterProvider().addFilter("DynamicFilter",filter);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);
        jacksonValue.setFilters(provider);
        return jacksonValue;
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue filteringList() {
        List<FilterBean>  bean =Arrays.asList(new FilterBean("value1", "value2", "value3"),
                new FilterBean("value23", "value34", "value45"));
        SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("field2");
        FilterProvider provider = new SimpleFilterProvider().addFilter("DynamicFilter",filter);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);
        jacksonValue.setFilters(provider);
        return jacksonValue;
    }



}
