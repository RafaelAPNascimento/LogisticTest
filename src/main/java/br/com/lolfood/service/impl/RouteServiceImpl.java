package br.com.lolfood.service.impl;

import br.com.lolfood.dao.RouteDao;
import br.com.lolfood.model.Route;
import br.com.lolfood.service.RouteService;
import jakarta.inject.Inject;

public class RouteServiceImpl implements RouteService {

    @Inject
    private RouteDao<Route> dao;

    @Override
    public Route[] getSuggestedRoutes() {
                
        return new Route[0];
    }

    @Override
    public Route confirm(Long id) {
        return null;
    }
}
