package io.github.ailtonbsj.service;

import io.github.ailtonbsj.model.Client;
import io.github.ailtonbsj.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private final ClientsRepository repository;

    public ClientsService(ClientsRepository repository){
        this.repository = repository;
    }

    public void salveClient(Client client){
        validateClient(client);
        this.repository.persist(client);
    }

    public void validateClient(Client client) {
        // apply validations
    }
}
