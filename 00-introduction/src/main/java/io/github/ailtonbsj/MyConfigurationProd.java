package io.github.ailtonbsj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class MyConfigurationProd {
    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de Vendas";
    }
}
