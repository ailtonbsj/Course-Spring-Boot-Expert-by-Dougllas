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
    public CommandLineRunner init(
            @Autowired Clients clients,
            @Autowired PurchaseOrders purchaseOrders) {
        return args -> {
            System.out.println("Inserting clients...");
            clients.save(new Client(null, "Fulano"));
            Client ciclano = new Client(null, "Ciclano");
            clients.save(ciclano);
            clients.findAll().forEach(System.out::println);

            System.out.println("Update one client...");
            Client u = clients.findAll().get(0);
            u.setName("Beltrano");
            clients.save(u);
            clients.findAll().forEach(System.out::println);

            System.out.println("Finding by name...");
            // clients.findByNameLike("%Bel%").forEach(System.out::println);
            clients.getAllByName("%lano%").forEach(System.out::println);

            if(clients.existsByName("Ciclano")) {
                System.out.println("Ciclano exists!!!");
            }

            System.out.println("Deleting one client...");
            clients.delete(u);
            clients.findAll().forEach(System.out::println);

            PurchaseOrder p = new PurchaseOrder();
            p.setClient(ciclano);
            p.setOrderDate(LocalDate.now());
            p.setAmount(BigDecimal.valueOf(100));
            purchaseOrders.save(p);

            ciclano = clients.findClientFetchOrders(ciclano.getId());
            System.out.println(ciclano);
            System.out.println(ciclano.getPurchaseOrders());
            purchaseOrders.findByClient(ciclano).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(UsingJPAApplication.class, args);
    }
}
