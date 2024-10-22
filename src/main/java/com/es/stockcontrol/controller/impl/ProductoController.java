package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProductoControllerAPI;
import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.service.ProductoService;

import java.util.List;

public class ProductoController implements ProductoControllerAPI {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public RespuestaHTTP<Producto> altaProducto(String idProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor) {
        try{

            // Validar la ID del producto

        } catch (Exception e) {
            return new RespuestaHTTP(500, "Error interno en el servidor", null);
        }

        productoService.agregarProducto(idProducto, nombreProducto, Float.parseFloat(precioSinIva), null, nombreProveedor);
        return null;
    }

    @Override
    public RespuestaHTTP<Producto> bajaProducto(String id) {

        return null;
    }

    @Override
    public RespuestaHTTP<Producto> modificarNombreProducto(String id, String nuevoNombre) {
        return null;
    }

    @Override
    public RespuestaHTTP<Producto> modificarStockProducto(String id, String nuevoStock) {
        return null;
    }

    @Override
    public RespuestaHTTP<Producto> getProducto(String id) {
        return null;
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosConStock() {
        return null;
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosSinStock() {
        return null;
    }
}
