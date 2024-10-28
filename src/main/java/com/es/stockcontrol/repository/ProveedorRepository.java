package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Collections;
import java.util.List;

public class ProveedorRepository {

    private EntityManager entityManager;

    public ProveedorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Proveedor> getProveedoresProducto(String idProducto) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            List<Proveedor> proveedores = entityManager.createQuery(
                            "SELECT p.proveedor FROM Producto p WHERE p.id = :idProducto", Proveedor.class)
                    .setParameter("idProducto", idProducto)
                    .getResultList();

            tx.commit();
            return proveedores;

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error al obtener proveedores de un producto: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Proveedor> getAllProveedores() {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            List<Proveedor> proveedores = entityManager.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList();
            tx.commit();
            return proveedores;
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error al obtener proveedores: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
