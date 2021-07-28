package br.com.lolfood.service.impl;

import br.com.lolfood.dao.ClientDao;
import br.com.lolfood.exception.BusinessException;
import br.com.lolfood.model.Client;
import br.com.lolfood.service.ClientService;
import jakarta.inject.Inject;

public class ClientServiceImpl implements ClientService {

    @Inject
    private ClientDao<Client> dao;

    @Override
    public void create(Client client) {
        dao.save(client);
    }

    @Override
    public void update(Client client) {
        try {
            dao.update(client);
        }
        catch (RuntimeException e) {
            throw new BusinessException(e.getMessage(), 404);
        }
    }

    @Override
    public Client find(Long id) {
        return dao.getById(id);
    }
}
