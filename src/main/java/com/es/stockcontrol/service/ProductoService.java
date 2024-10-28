package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProductoRepository;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class ProductoService {

    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto agregarProducto(String idProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor, String nombreCategoria, float ivaRate) {
        if (nombreProducto == null || nombreProducto.length() > 50 || nombreProveedor == null || nombreProveedor.length() > 50 ||
                nombreCategoria == null || nombreCategoria.length() > 50) {
            return null;
        }

        if (precioSinIva == null || !precioSinIva.matches("\\d+(\\.\\d+)?")) {
            return null;
        }

        float precioSinIvaFloat = Float.parseFloat(precioSinIva);

        if (precioSinIvaFloat <= 0) {
            return null;
        }

        // Crear proveedor
        Proveedor proveedor = new Proveedor(nombreProveedor, direccionProveedor);

        //Generar id
        String idGenerado = generarId(nombreProducto, idProducto, nombreProveedor);

        // Crear producto
        Producto producto = new Producto(nombreCategoria, nombreProducto, descripcionProducto, precioSinIvaFloat, (float) (precioSinIvaFloat * 1.21), Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)), proveedor);
        producto.setId(idProducto);

        // Establecer la fecha de alta
        producto.setFechaAlta(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)));

        productoRepository.guardar(proveedor, producto);
        return producto;
    }

    public boolean bajaProducto(String id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            productoRepository.eliminar(producto);
            return true;
        }
        return false;
    }

    public boolean modificarNombreProducto(String id, String nuevoNombre) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            producto.setNombre(nuevoNombre);
            productoRepository.actualizar(producto);
            return true;
        }
        return false;
    }

    public void modificarStockProducto(String id, int nuevoStock) {
        productoRepository.modificarStock(id, nuevoStock);
    }

    public Producto getProducto(String id) {
        return productoRepository.buscarPorId(id);
    }

    public List<Producto> getProductosConStock() {
        return productoRepository.getProductosConStock();
    }

    public List<Producto> getProductosSinStock() {
        return productoRepository.getProductosSinStock();
    }

    private String generarId(String nombreProducto, String idProducto, String nombreProveedor) {
        // Obtener las 3 primeras letras de cada parámetro, asegurando que el tamaño mínimo es 3
        String categoria = idProducto.substring(0, Math.min(3, idProducto.length())).toUpperCase();
        String nombre = nombreProducto.substring(0, Math.min(3, nombreProducto.length())).toUpperCase();
        String proveedor = nombreProveedor.substring(0, Math.min(3, nombreProveedor.length())).toUpperCase();

        // Concatenar los valores para formar el ID
        return categoria + nombre + proveedor;

        /*
        EJEMPLO:

        String id = generarId("Ropa", "Camisa", "Zara");
        Resultado: ROPCAMZAR
        */
    }
}
