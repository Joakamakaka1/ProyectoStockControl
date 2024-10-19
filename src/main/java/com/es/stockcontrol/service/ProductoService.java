package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.repository.ProductoRepository;

import java.util.Date;

public class ProductoService {

    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto agregarProducto(String categoria, String nombre, Float precioSinIva, Float precioConIva, String proveedor) {
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
    private String generarId(String categoria, String nombre, String proveedor) {
        String categoriaAbreviada = categoria.length() >= 3 ? categoria.substring(0, 3) : categoria;
        String nombreAbreviado = nombre.length() >= 3 ? nombre.substring(0, 3) : nombre;
        String proveedorAbreviado = proveedor.length() >= 3 ? proveedor.substring(0, 3) : proveedor;
        return categoriaAbreviada + nombreAbreviado + proveedorAbreviado;
    }

    private Float calcularPrecioConIva(Float precioSinIva) {
        final float IVA = 0.21f;
        return precioSinIva * (1 + IVA);
    }
}
