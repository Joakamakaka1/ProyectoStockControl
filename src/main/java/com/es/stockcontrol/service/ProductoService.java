package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProductoRepository;

import java.util.Date;
import java.util.List;

public class ProductoService {

    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto agregarProducto(String categoria, String nombre, Float precioSinIva, Float precioConIva, Proveedor proveedor) {
        if (categoria == null || categoria.length() < 3 || categoria.length() > 50) {
            return null;
        }
        if (nombre == null || nombre.length() < 3 || nombre.length() > 50) {
            return null;
        }
        if (precioSinIva == null || precioConIva == null) {
            return null;
        }

        String id = generarId(categoria, nombre, proveedor);

        if (precioConIva == null) {
            precioConIva = calcularPrecioConIva(precioSinIva);
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setId(id);
        nuevoProducto.setCategoria(categoria);
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setPrecioSinIva(precioSinIva);
        nuevoProducto.setPrecioConIva(precioConIva);
        nuevoProducto.setFechaAlta(new Date());

        productoRepository.guardar(nuevoProducto);
        return nuevoProducto;
    }

    // Generamos la ID del producto ya que no tiene la anotacion para hacerlo en este caso
    public String generarId(String categoria, String nombre, Proveedor proveedor) {
        String categoriaAbreviada = categoria.length() >= 3 ? categoria.substring(0, 3) : categoria;
        String nombreAbreviado = nombre.length() >= 3 ? nombre.substring(0, 3) : nombre;
        String proveedorAbreviado = proveedor != null ? proveedor.getNombre().length() >= 3 ? proveedor.getNombre().substring(0, 3) : proveedor.getNombre() : "";
        return categoriaAbreviada + nombreAbreviado + proveedorAbreviado;
    }

    public Float calcularPrecioConIva(Float precioSinIva) {
        final float IVA = 0.21f;
        return precioSinIva * (1 + IVA);
    }

    public Producto getProducto(String id) {
        return productoRepository.buscarPorId(id);
    }

    public Producto actualizarProducto(String id, String categoria, String nombre, Float precioSinIva, Float precioConIva, Proveedor proveedor) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto == null) {
            return null;
        }
        if (categoria != null && !categoria.isEmpty()) {
            producto.setCategoria(categoria);
        }
        if (nombre != null && !nombre.isEmpty()) {
            producto.setNombre(nombre);
        }
        if (proveedor != null) {
            producto.setProveedor(proveedor);
        }
        if (precioSinIva != null) {
            producto.setPrecioSinIva(precioSinIva);
        }
        if (precioConIva != null) {
            producto.setPrecioConIva(precioConIva);
        }
        productoRepository.actualizar(producto);
        return producto;
    }

    public void eliminarProducto(String id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto == null) {
            return;
        }
        productoRepository.eliminar(producto);
    }
}
