package com.zz.rest.demo.config.spring.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.zz.rest.demo.controller" })
@Import({ MvcConfiguration.class })
public class AppConfiguration {

}