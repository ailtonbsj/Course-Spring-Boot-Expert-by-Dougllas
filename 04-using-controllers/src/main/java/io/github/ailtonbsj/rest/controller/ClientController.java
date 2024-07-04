package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.repositories.Clients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        try {
            clients.save(client);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
