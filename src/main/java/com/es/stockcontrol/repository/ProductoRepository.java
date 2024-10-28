package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.List;

public class ProductoRepository {

    private EntityManager entityManager; // entityManager se encarga de administrar los datos del modelo

    public ProductoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void guardar(Proveedor proveedor, Producto producto) { // POST
        EntityTransaction tx = entityManager.getTransaction(); //Crea la transacción
        tx.begin(); //Inicia la transacción
        try {
            entityManager.persist(producto); // Guarda el objeto en la base de datos
            entityManager.persist(proveedor);
            tx.commit(); //Confirma la transacción
        } catch (Exception e) {
            tx.rollback(); //Deshace la transacción
            throw e;
        }
    }

    public Producto buscarPorId(String s) { // GET by id
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Producto producto = entityManager.find(Producto.class, s);
            tx.commit();
            return producto;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

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

    public List<Producto> buscarTodos() { // GET all (optional)
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList(); // Devuelve una lista de productos
    }

    public List<Producto> getProductosSinStock() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            List<Producto> productos = entityManager.createQuery("SELECT p FROM Producto p WHERE p.stock = 0", Producto.class).getResultList();
            tx.commit(); // Confirma la transacción
            return productos;
        } catch (Exception e) {
            tx.rollback(); // Deshace la transacción
            System.out.println("Error al obtener productos sin stock: " + e.getMessage());
            return Collections.emptyList(); // Retorna una lista vacía en caso de error
        }
    }

    public List<Producto> getProductosConStock() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            List<Producto> productos = entityManager.createQuery("SELECT p FROM Producto p WHERE p.stock > 0", Producto.class).getResultList();
            tx.commit();
            return productos;
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error al obtener productos con stock: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void modificarStock(String id, Integer stock) {
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
