package io.github.ailtonbsj;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.repositories.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsingJPAApplication {
    @Bean
    public CommandLineRunner init(@Autowired Clients clients) {
        return args -> {
            System.out.println("Inserting clients...");
            clients.save(new Client(null, "Fulano"));
            clients.save((new Client(null, "Ciclano")));
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
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(UsingJPAApplication.class, args);
    }
}
