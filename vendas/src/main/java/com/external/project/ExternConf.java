package com.external.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternConf {
    @Bean(name = "externalConf")
    String externalConf() {
        return "external-conf-1";
    }
}
