package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserRepository implements BaseRepository<User, String> {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void guardar(User entity) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public User buscarPorId(String nombre_usuario) {
        return entityManager.find(User.class, nombre_usuario);
    }

    @Override
    public void actualizar(User user) {
    EntityTransaction tx = entityManager.getTransaction();
    tx.begin();
    try{
        entityManager.merge(user);
        tx.commit();
    }catch (Exception e){
        tx.rollback();
        throw e;
    }
    }

    @Override
    public void eliminar(User user) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.remove(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> buscarTodos() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
