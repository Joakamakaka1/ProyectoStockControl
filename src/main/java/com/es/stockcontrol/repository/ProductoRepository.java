package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class ProductoRepository implements BaseRepository<Producto, String> {

    private EntityManager entityManager; // entityManager se encarga de administrar los datos del modelo

    public ProductoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void guardar(Producto producto) { // POST
        EntityTransaction tx = entityManager.getTransaction(); //Crea la transacción
        tx.begin(); //Inicia la transacción
        try {
            entityManager.persist(producto); // Guarda el objeto en la base de datos
            tx.commit(); //Confirma la transacción
        } catch (Exception e) {
            tx.rollback(); //Deshace la transacción
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
            entityManager.merge(producto); // Actualiza el objeto
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
            entityManager.remove(producto); // Elimina el objeto
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Producto> buscarTodos() { // GET all (optional)
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList(); // Devuelve una lista de productos
    }

    public List<Producto> getProductosSinStock() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            // Devuelve una lista de productos sin stock
            return entityManager.createQuery("SELECT p FROM Producto p WHERE p.stock = 0", Producto.class).getResultList();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public List<Producto> getProductosConStock() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            // Devuelve una lista de productos con stock
            return entityManager.createQuery("SELECT p FROM Producto p WHERE p.stock > 0", Producto.class).getResultList();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    public void modificarStock(String id, int stock) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            // Actualiza el stock del producto
            entityManager.createQuery("UPDATE Producto p SET p.stock = :stock WHERE p.id = :id")
                    .setParameter("stock", stock)
                    .setParameter("id", id)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
