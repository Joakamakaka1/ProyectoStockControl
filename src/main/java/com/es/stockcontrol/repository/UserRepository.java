package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * The type User repository.
 */
public class UserRepository {

    private EntityManager entityManager;

    /**
     * Instantiates a new User repository.
     *
     * @param entityManager the entity manager
     */
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Guardar.
     *
     * @param entity the entity
     */
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

    /**
     * Buscar por id user.
     *
     * @param nombre_usuario the nombre usuario
     * @return the user
     */
    public User buscarPorId(String nombre_usuario) {
        return entityManager.find(User.class, nombre_usuario);
    }

    /**
     * Actualizar.
     *
     * @param user the user
     */
    public void actualizar(User user) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.merge(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    /**
     * Eliminar.
     *
     * @param user the user
     */
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

    /**
     * Buscar todos list.
     *
     * @return the list
     */
    public List<User> buscarTodos() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
