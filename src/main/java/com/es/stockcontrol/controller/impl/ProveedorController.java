package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProveedorControllerAPI;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.service.ProveedorService;

import java.util.List;

public class ProveedorController implements ProveedorControllerAPI {

    private ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(Long idProducto) {
        try {
            if (proveedorService.getProveedor(idProducto) == null) {
                return new RespuestaHTTP<>(404, "El proveedor no existe", null);
            }
            return new RespuestaHTTP<>(200, "Proveedores encontrado", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {
        try {
            if (proveedorService.getTodosProveedores() == null) {
                return new RespuestaHTTP<>(404, "El proveedor no existe", null);
            }
            return new RespuestaHTTP<>(200, "Proveedores encontrado", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }
}
