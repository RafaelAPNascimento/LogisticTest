package br.com.lolfood.dao.impl;

import br.com.lolfood.dao.ClientDao;
import br.com.lolfood.model.Client;
import br.com.lolfood.service.impl.Database;

public class ClientDaoImpl implements ClientDao<Client> {


    @Override
    public Client getById(Object id) {
        return Database.getClient(id);
    }

    @Override
    public Client salvar(Client entity) {
        return Database.insertClient(entity);
    }

    @Override
    public Client update(Client entity) {
        return Database.updateClient(entity);
    }

    @Override
    public Client delete(Client entity) {
        return Database.deleteClient(entity);
    }

    @Override
    public Class<Client> getEntityClass() {
        return Client.class;
    }
}
