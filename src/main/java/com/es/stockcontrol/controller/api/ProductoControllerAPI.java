package com.es.stockcontrol.controller.api;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.RespuestaHTTP;

import java.util.List;

public interface ProductoControllerAPI {

    RespuestaHTTP<Producto> altaProducto(String idProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor);
    RespuestaHTTP<Producto> bajaProducto(String id);
    RespuestaHTTP<Producto> modificarNombreProducto(String id, String nuevoNombre);
    RespuestaHTTP<Producto> modificarStockProducto(String id, String nuevoStock);
    RespuestaHTTP<Producto> getProducto(String id);
    RespuestaHTTP<List<Producto>> getProductosConStock();
    RespuestaHTTP<List<Producto>> getProductosSinStock();
}
