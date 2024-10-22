package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ProductoRepository implements BaseRepository<Producto, String> {

    private EntityManager entityManager;

    public ProductoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void guardar(Producto producto) { // POST
        EntityTransaction tx = entityManager.getTransaction();//Crea la transacción
        tx.begin();//Inicia la transacción
        try {
            entityManager.persist(producto);//Guarda el objeto en la base de datos
            tx.commit();//Confirma la transacción
        } catch (Exception e) {
            tx.rollback();//Deshace la transacción
            throw e;
        }
    }

    @Override
    public Producto buscarPorId(String s) { // GET by id
        return entityManager.find(Producto.class, s);
    }

    @Override
    public void actualizar(Producto producto) { // PUT
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.merge(producto);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void eliminar(Producto producto) { // DELETE
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.remove(producto);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Producto> buscarTodos() { // GET all (optional)
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }
}
