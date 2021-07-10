package br.com.lolfood.service;

import br.com.lolfood.model.Client;

public interface ClientService {

    void create(Client client);

    void update(Client client);

    Client find(Long id);
}
