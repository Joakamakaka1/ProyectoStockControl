package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProveedorRepository;

public class ProveedorService {

    private ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor agregarProveedor(String nombre, String direccion) {
        if (nombre == null || nombre.isEmpty() || direccion == null || direccion.isEmpty()) {
            return null;
        }
        if (proveedorRepository.buscarTodos().stream().anyMatch(p -> p.getNombre().equals(nombre))) {
            return null;
        }
        if (nombre.length()<0 || nombre.length()>50) {
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedorRepository.guardar(proveedor);
        return proveedor;
    }

    public Proveedor GetProveedor(Long id) {
        return proveedorRepository.buscarPorId(id);

    }


    public Proveedor actualizarProveedor(Long id, String nombre, String direccion) {
        Proveedor proveedor = proveedorRepository.buscarPorId(id);
        if (proveedor==null) {
            return null;
        }
        if (nombre != null && !nombre.isEmpty()) {
            if (nombre.length()<0 || nombre.length()>50) {
                return null;
            }
            proveedor.setNombre(nombre);
        }
        if (direccion != null && !direccion.isEmpty()) {
            if (direccion.length()<0 || direccion.length()>50) {
                return null;
            }
            proveedor.setDireccion(direccion);
        }
        proveedorRepository.actualizar(proveedor);
        return proveedor;
    }

    public void eliminarProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.buscarPorId(id);
        if (proveedor != null) {
            proveedorRepository.eliminar(proveedor);
        }
    }


}
