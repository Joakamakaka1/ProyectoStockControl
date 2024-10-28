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
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String idProducto) {
        try {
            List<Proveedor> proveedores = proveedorService.getProveedoresProducto(idProducto);

            if (proveedores.isEmpty()) {
                return new RespuestaHTTP<>(404, "No existen proveedores para este producto", proveedores);
            }

            return new RespuestaHTTP<>(200, "Proveedores encontrados", proveedores);

        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {
        try {
            List<Proveedor> proveedores = proveedorService.getTodosProveedores();
            if (proveedores.isEmpty()) {
                return new RespuestaHTTP<>(404, "No existen proveedores registrados", null);
            }
            return new RespuestaHTTP<>(200, "Proveedores encontrados", proveedores);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }
}
