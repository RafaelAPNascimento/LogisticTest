package br.com.lolfood.service;

import br.com.lolfood.model.Route;

public interface RouteService {

    Route[] getSuggestedRoutes();

    Route confirm(Long id);
}
