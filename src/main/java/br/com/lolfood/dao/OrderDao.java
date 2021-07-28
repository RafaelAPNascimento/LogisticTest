package br.com.lolfood.dao;

import java.util.List;

public interface OrderDao<T> extends GenericDao<T> {


    List<T> getByRestaurant(Long id);
}
