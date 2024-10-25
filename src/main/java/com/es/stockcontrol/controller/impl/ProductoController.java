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
        try {
            if (productoService.getProducto(idProducto) != null) {
                return new RespuestaHTTP<>(409, "El producto ya existe", null);
            }

            Producto productoCreado = productoService.agregarProducto(idProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor, "CategoriaGenerica", 0.21f);

            return (productoCreado != null) ?
                    new RespuestaHTTP<>(201, "Producto creado exitosamente", productoCreado) :
                    new RespuestaHTTP<>(400, "Error al crear el producto", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> bajaProducto(String id) {
        try {
            if (productoService.getProducto(id) == null) {
                return new RespuestaHTTP<Producto>(404, "El producto no existe", null);
            }
            return productoService.bajaProducto(id) ?
                    new RespuestaHTTP<Producto>(200, "Producto eliminado", null) :
                    new RespuestaHTTP<Producto>(400, "Error interno en el servidor", null);
        } catch (Exception e) {
            return new RespuestaHTTP<Producto>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> modificarNombreProducto(String id, String nuevoNombre) {
        try{
            if (productoService.modificarNombreProducto(id, nuevoNombre) == null) {
                return new RespuestaHTTP<Producto>(404, "El producto no existe", null);
            }
            return productoService.modificarNombreProducto(id, nuevoNombre) ?
                    new RespuestaHTTP<Producto>(200, "Producto modificado", null) :
                    new RespuestaHTTP<Producto>(400, "Error interno en el servidor", null);
        }catch (Exception e) {
            return new RespuestaHTTP<Producto>(500, "Error interno en el servidor", null);
        }

    }

    @Override
    public RespuestaHTTP<Producto> modificarStockProducto(String id, int nuevoStock) {
        try{
            if (productoService.modificarStockProducto(id, nuevoStock) == null) {
                return new RespuestaHTTP<Producto>(404, "El producto no existe", null);
            }
            return productoService.modificarStockProducto(id, nuevoStock) ?
                    new RespuestaHTTP<Producto>(200, "Producto modificado", null) :
                    new RespuestaHTTP<Producto>(400, "Error interno en el servidor", null);
        }catch (Exception e) {
            return new RespuestaHTTP<Producto>(500, "Error interno en el servidor", null);
        }
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
        try {
            if (productoService.getProductosConStock() == null) {
                return new RespuestaHTTP<List<Producto>>(404, "El producto no existe", null);
            }
            return new RespuestaHTTP<List<Producto>>(200, "Producto encontrado", null);

        }catch (Exception e) {
            return new RespuestaHTTP<List<Producto>>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosSinStock() {
        try {
            if (productoService.getProductosSinStock() == null) {
                return new RespuestaHTTP<List<Producto>>(404, "El producto no existe", null);
            }
            return new RespuestaHTTP<List<Producto>>(200, "Producto encontrado", null);
        }catch (Exception e) {
            return new RespuestaHTTP<List<Producto>>(500, "Error interno en el servidor", null);
        }
    }
}
