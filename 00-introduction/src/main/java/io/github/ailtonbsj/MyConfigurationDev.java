package io.github.ailtonbsj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@DevConf
public class MyConfigurationDev {
    @Bean(name = "applicationName")
    public String applicationName() {
        return "Sistema de Vendas DEV";
    }

    @Bean
    public CommandLineRunner executar() {
        return args -> {
          System.out.println("RUNNING DEV CONF");
        };
    }
}
