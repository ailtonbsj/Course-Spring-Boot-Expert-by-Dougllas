package io.github.ailtonbsj;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import io.github.ailtonbsj.domain.repositories.Clients;
import io.github.ailtonbsj.domain.repositories.PurchaseOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class UsingJPAApplication {
    @Bean
    CommandLineRunner commandLineRunner(@Autowired Clients clients) {
        return args -> {
            Client c = new Client(null, "Fulano");
            clients.save(c);
            System.out.println("New user created!");
            System.out.println(c);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(UsingJPAApplication.class, args);
    }
}
