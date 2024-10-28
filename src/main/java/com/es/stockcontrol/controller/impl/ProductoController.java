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
            //System.out.println("Recibiendo solicitud para agregar producto: ID=" + idProducto + ", Nombre=" + nombreProducto);

            if (productoService.getProducto(idProducto) != null) {
                //System.out.println("El producto ya existe: ID=" + idProducto);
                return new RespuestaHTTP<>(409, "El producto ya existe", null);
            }

            Producto productoCreado = productoService.agregarProducto(idProducto, nombreProducto, precioSinIva, descripcionProducto, nombreProveedor, direccionProveedor, "CategoriaGenerica", 0.21f);

            if (productoCreado != null) {
                //System.out.println("Producto creado exitosamente: " + productoCreado);
                return new RespuestaHTTP<>(201, "Producto creado exitosamente", productoCreado);
            } else {
                //System.out.println("Error al crear el producto. Producto devuelto es nulo.");
                return new RespuestaHTTP<>(400, "Error al crear el producto", null);
            }
        } catch (Exception e) {
            //System.out.println("Error interno en el servidor: " + e.getMessage());
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
        try {
            Producto producto = productoService.getProducto(id);
            if (producto == null) {
                return new RespuestaHTTP<>(404, "El producto no existe", null);
            }

            boolean modificado = productoService.modificarNombreProducto(id, nuevoNombre);
            return modificado ?
                    new RespuestaHTTP<>(200, "Nombre del producto modificado", null) :
                    new RespuestaHTTP<>(400, "Error al modificar el nombre del producto", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> modificarStockProducto(String id, String nuevoStock) {
        try {
            Producto producto = productoService.getProducto(id);
            if (producto == null) {
                return new RespuestaHTTP<>(404, "El producto no existe", null);
            }

            if (nuevoStock == null || !nuevoStock.matches("\\d+")) {
                return new RespuestaHTTP<>(400, "El stock debe ser un número entero válido", null);
            }

            int nuevoStockInt = Integer.parseInt(nuevoStock);
            productoService.modificarStockProducto(id, nuevoStockInt);
            return new RespuestaHTTP<>(200, "Stock del producto modificado", null);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<Producto> getProducto(String id) {
        try {
            Producto producto = productoService.getProducto(id);
            if (producto == null) {
                return new RespuestaHTTP<>(404, "El producto no existe", null);
            }
            return new RespuestaHTTP<>(200, "Producto encontrado", producto);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosConStock() {
        try {
            List<Producto> productos = productoService.getProductosConStock();
            if (productos.isEmpty()) {
                return new RespuestaHTTP<>(404, "No hay productos con stock", null);
            }
            return new RespuestaHTTP<>(200, "Productos encontrados", productos);
        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosSinStock() {
        try {
            List<Producto> productos = productoService.getProductosSinStock();

            if (productos.isEmpty()) {
                return new RespuestaHTTP<>(404, "No hay productos sin stock", productos);
            }

            return new RespuestaHTTP<>(200, "Productos sin stock encontrados", productos);

        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error interno en el servidor", null);
        }
    }
}
