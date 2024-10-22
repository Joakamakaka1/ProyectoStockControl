package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProveedorRepository;

import java.util.List;

public class ProveedorService {

    private ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor getProveedor(Long id) {
        return proveedorRepository.buscarPorId(id);
    }

    public List<Proveedor> getTodosProveedores() {
        return proveedorRepository.buscarTodos();
    }
}
