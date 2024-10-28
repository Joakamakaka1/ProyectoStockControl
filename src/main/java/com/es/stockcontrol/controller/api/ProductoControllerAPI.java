package com.es.stockcontrol.controller.api;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.RespuestaHTTP;

import java.util.List;

/**
 * The interface Producto controller api.
 */
public interface ProductoControllerAPI {

    /**
     * Alta producto respuesta http.
     *
     * @param idProducto          the id producto
     * @param nombreProducto      the nombre producto
     * @param precioSinIva        the precio sin iva
     * @param descripcionProducto the descripcion producto
     * @param nombreProveedor     the nombre proveedor
     * @param direccionProveedor  the direccion proveedor
     * @return the respuesta http
     */
    RespuestaHTTP<Producto> altaProducto(String idProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor);

    /**
     * Baja producto respuesta http.
     *
     * @param id the id
     * @return the respuesta http
     */
    RespuestaHTTP<Producto> bajaProducto(String id);

    /**
     * Modificar nombre producto respuesta http.
     *
     * @param id          the id
     * @param nuevoNombre the nuevo nombre
     * @return the respuesta http
     */
    RespuestaHTTP<Producto> modificarNombreProducto(String id, String nuevoNombre);

    /**
     * Modificar stock producto respuesta http.
     *
     * @param id         the id
     * @param nuevoStock the nuevo stock
     * @return the respuesta http
     */
    RespuestaHTTP<Producto> modificarStockProducto(String id, String nuevoStock);

    /**
     * Gets producto.
     *
     * @param id the id
     * @return the producto
     */
    RespuestaHTTP<Producto> getProducto(String id);

    /**
     * Gets productos con stock.
     *
     * @return the productos con stock
     */
    RespuestaHTTP<List<Producto>> getProductosConStock();

    /**
     * Gets productos sin stock.
     *
     * @return the productos sin stock
     */
    RespuestaHTTP<List<Producto>> getProductosSinStock();
}
