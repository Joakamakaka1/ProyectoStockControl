package com.es.stockcontrol.repository;

import java.util.List;

/**
 * The interface Base repository.
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 */
public interface BaseRepository<T, ID> { // NO SE ESTA USANDO
    /**
     * Guardar.
     *
     * @param entity the entity
     */
    void guardar(T entity); // POST

    /**
     * Buscar por id t.
     *
     * @param id the id
     * @return the t
     */
    T buscarPorId(ID id); // GET

    /**
     * Actualizar.
     *
     * @param entity the entity
     */
    void actualizar(T entity); // PUT

    /**
     * Eliminar.
     *
     * @param entity the entity
     */
    void eliminar(T entity); // DELETE

    /**
     * Buscar todos list.
     *
     * @return the list
     */
    List<T> buscarTodos(); // OPTIONAL GET
}