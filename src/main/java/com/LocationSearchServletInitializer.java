package com;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class LocationSearchServletInitializer extends SpringBootServletInitializer{

	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	         return application.sources(LocationSearchApplication.class);
	    }
}