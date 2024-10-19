package com.es.stockcontrol.service;

import com.es.stockcontrol.repository.ProveedorRepository;

public class ProveedorService {

    private ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
}
