package io.github.ailtonbsj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.external.project",
        "io.github.ailtonbsj"
})
@RestController
public class VendasApplication {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @Autowired
    @Qualifier("externalConf")
    private String externalString;

    @Dog
    private Animal animal;

    @Bean
    public CommandLineRunner run(){
        return args -> {
            this.animal.doNoise();
        };
    }

    @Value("${application.otherName}")
    private String otherName;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName + " " + externalString + "/" + otherName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
