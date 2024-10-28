package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.List;

/**
 * The type Producto repository.
 */
public class ProductoRepository {

    private EntityManager entityManager; // entityManager se encarga de administrar los datos del modelo

    /**
     * Instantiates a new Producto repository.
     *
     * @param entityManager the entity manager
     */
    public ProductoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Guardar.
     *
     * @param proveedor the proveedor
     * @param producto  the producto
     */
    public void guardar(Proveedor proveedor, Producto producto) { // POST
        EntityTransaction tx = entityManager.getTransaction(); //Crea la transacción
        tx.begin(); //Inicia la transacción
        try {
            entityManager.persist(producto); // Guarda el objeto en la base de datos
            entityManager.persist(proveedor);
            tx.commit(); //Confirma la transacción
            //System.out.println("Producto insertado: " + producto);
        } catch (Exception e) {
            tx.rollback(); //Deshace la transacción
            throw e;
        }
    }

    /**
     * Buscar por id producto.
     *
     * @param id the id
     * @return the producto
     */
    public Producto buscarPorId(String id) { // GET by id
        //System.out.println("Buscando producto con ID: " + id);
        return entityManager.find(Producto.class, id);
    }

    /**
     * Actualizar.
     *
     * @param producto the producto
     */
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

    /**
     * Eliminar.
     *
     * @param producto the producto
     */
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

    /**
     * Buscar todos list.
     *
     * @return the list
     */
    public List<Producto> buscarTodos() { // GET all (optional)
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList(); // Devuelve una lista de productos
    }

    /**
     * Gets productos sin stock.
     *
     * @return the productos sin stock
     */
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

    /**
     * Gets productos con stock.
     *
     * @return the productos con stock
     */
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

    /**
     * Modificar stock.
     *
     * @param id    the id
     * @param stock the stock
     */
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
