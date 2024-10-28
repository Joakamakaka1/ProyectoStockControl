package com.es.stockcontrol.controller.api;

import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.model.RespuestaHTTP;

import java.util.List;

/**
 * The interface Proveedor controller api.
 */
public interface ProveedorControllerAPI {

    /**
     * Gets proveedores producto.
     *
     * @param idProducto the id producto
     * @return the proveedores producto
     */
    RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String idProducto); // La ID debería de ser Long no String ?

    /**
     * Gets todos proveedores.
     *
     * @return the todos proveedores
     */
    RespuestaHTTP<List<Proveedor>> getTodosProveedores();
}
