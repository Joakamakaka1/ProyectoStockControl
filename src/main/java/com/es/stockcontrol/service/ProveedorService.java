package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProveedorRepository;

import java.util.List;

/**
 * The type Proveedor service.
 */
public class ProveedorService {

    private ProveedorRepository proveedorRepository;

    /**
     * Instantiates a new Proveedor service.
     *
     * @param proveedorRepository the proveedor repository
     */
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    /**
     * Gets proveedores producto.
     *
     * @param id the id
     * @return the proveedores producto
     */
    public List<Proveedor> getProveedoresProducto(String id) {
        return proveedorRepository.getProveedoresProducto(id);
    }

    /**
     * Gets todos proveedores.
     *
     * @return the todos proveedores
     */
    public List<Proveedor> getTodosProveedores() {
        return proveedorRepository.getAllProveedores();
    }
}
