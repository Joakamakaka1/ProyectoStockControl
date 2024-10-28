package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProveedorRepository;

import java.util.List;

public class ProveedorService {

    private ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> getProveedoresProducto(String id) {
        return proveedorRepository.getProveedoresProducto(id);
    }

    public List<Proveedor> getTodosProveedores() {
        return proveedorRepository.getAllProveedores();
    }
}
