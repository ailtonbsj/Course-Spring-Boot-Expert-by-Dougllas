package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.repositories.Clients;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    private Clients clients;

    public ClientController(Clients clients) {
        this.clients = clients;
    }

    @GetMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Integer id){
        Optional<Client> client = clients.findById(id);
        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clients")
    public ResponseEntity save(@RequestBody Client client){
        Client savedClient = clients.save(client);
        return ResponseEntity.ok(savedClient);
    }

    @DeleteMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Client> client = clients.findById(id);
        if(client.isPresent()){
            clients.delete(client.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Client client){
        return clients.findById(id).map(existent -> {
            client.setId(existent.getId());
            clients.save(client);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/clients")
    public ResponseEntity find(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Client> list = clients.findAll(example);
        return ResponseEntity.ok(list);
    }
}
