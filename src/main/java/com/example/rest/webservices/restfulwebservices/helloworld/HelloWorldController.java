package com.example.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping( path = "/hello-world")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping( path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    @GetMapping( path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        //throw new RuntimeException("error throw");
        return new HelloWorldBean(String.format("hello world ,%s", name));
    }

    @GetMapping( path = "/hello-world-internationalization")
    public String helloWorldInternationalization(){
        return messageSource.getMessage("org.test.first",null,"Default", LocaleContextHolder.getLocale());
    }

}
