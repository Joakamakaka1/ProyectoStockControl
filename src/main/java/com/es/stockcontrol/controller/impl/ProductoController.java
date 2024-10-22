package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProductoControllerAPI;
import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
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
        try {
            if (idProducto.equals(productoService.getProducto(idProducto).getId())) {
                return new RespuestaHTTP<Producto>(409, "El producto ya existe", null);
            }
            if (productoService.agregarProducto(idProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor) == null) {
                return new RespuestaHTTP<Producto>(400, "Error al insertar el producto", null);
            }
            return new RespuestaHTTP<Producto>(201, "Producto creado", null);
        } catch (Exception e) {
            return new RespuestaHTTP<Producto>(500, "Error interno en el servidor", null);
        }
    }


    @Override
    public RespuestaHTTP<Producto> bajaProducto(String id) {
        try {
            if (productoService.getProducto(id) == null) {
                return new RespuestaHTTP<Producto>(404, "El producto no existe", null);
            }
            return productoService.eliminarProducto(id) ?
                    new RespuestaHTTP<Producto>(200, "Producto eliminado", null) :
                    new RespuestaHTTP<Producto>(400, "Error interno en el servidor", null);
        } catch (Exception e) {
            return new RespuestaHTTP<Producto>(500, "Error interno en el servidor", null);
        }
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
        try {
            if (productoService.getProducto(id) == null) {
                return new RespuestaHTTP<Producto>(404, "El producto no existe", null);
            }
            return new RespuestaHTTP<Producto>(200, "Producto encontrado", null);
        } catch (Exception e) {
            return new RespuestaHTTP<Producto>(500, "Error interno en el servidor", null);
        }
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
