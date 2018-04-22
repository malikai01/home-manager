package com.mlk.home.config;

import com.mlk.home.ApplicationWeb;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by malikai on 2018-4-17.
 */
@Configuration
public class WebInitializer extends SpringBootServletInitializer{
    public WebInitializer() {
        super();
        setRegisterErrorPageFilter(false); // disable ErrorPageFilter
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationWeb.class);
    }
}
