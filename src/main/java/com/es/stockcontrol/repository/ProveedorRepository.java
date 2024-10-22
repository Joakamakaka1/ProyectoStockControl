package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ProveedorRepository implements BaseRepository<Proveedor, Long> {

    private EntityManager entityManager;//entitymanager se encarga de administrar los datos del modelo

    public ProveedorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void guardar(Proveedor proveedor) { // POST
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.persist(proveedor);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Proveedor buscarPorId(Long id) { // GET by id
        return entityManager.find(Proveedor.class, id);
    }

    @Override
    public void actualizar(Proveedor proveedor) { // UPDATE
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityManager.merge(proveedor);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void eliminar(Proveedor proveedor) { // DELETE
        EntityTransaction tx =  entityManager.getTransaction();
        tx.begin();
        try{
            entityManager.remove(proveedor);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            throw e;
        }

    }

    @Override
    public List<Proveedor> buscarTodos() { // GET all (optional)
        return entityManager.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList();
    }
}
