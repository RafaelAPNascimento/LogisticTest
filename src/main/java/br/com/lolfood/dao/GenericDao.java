package br.com.lolfood.dao;

public interface GenericDao<E> {

    E getById(Object id);

    E salvar(E entity);

    E update(E entity);

    E delete(E entity);

    Class<E> getEntityClass();
}
