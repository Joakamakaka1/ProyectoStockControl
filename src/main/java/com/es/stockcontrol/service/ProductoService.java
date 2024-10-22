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

    public Producto agregarProducto(String idProducto, String nombreProducto, String precioSinIvaStr, String descripcionProducto, String nombreProveedor, String direccionProveedor) {
        if (precioSinIvaStr == null || precioSinIvaStr.isEmpty()) {
            return null;
        }

        Float precioSinIva;
        try {
            precioSinIva = Float.parseFloat(precioSinIvaStr);
        } catch (NumberFormatException e) {
            return null;
        }

        Float precioConIva = calcularPrecioConIva(precioSinIva);

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombreProveedor);
        proveedor.setDireccion(direccionProveedor);

        String id = generarId(idProducto, nombreProducto, proveedor);

        Producto nuevoProducto = new Producto();
        nuevoProducto.setId(id);
        nuevoProducto.setCategoria(idProducto);
        nuevoProducto.setNombre(nombreProducto);
        nuevoProducto.setDescripcion(descripcionProducto);
        nuevoProducto.setPrecioSinIva(precioSinIva);
        nuevoProducto.setPrecioConIva(precioConIva);
        nuevoProducto.setFechaAlta(new Date());
        nuevoProducto.setProveedor(proveedor);
        
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

    public Producto actualizarProducto(String id, String categoria, String nombre, String precioSinIvaStr, String nombreProveedor, String direccionProveedor) {
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

        if (nombreProveedor != null && direccionProveedor != null) {
            Proveedor proveedor = producto.getProveedor();
            if (proveedor == null) {
                proveedor = new Proveedor();
            }
            proveedor.setNombre(nombreProveedor);
            proveedor.setDireccion(direccionProveedor);
            producto.setProveedor(proveedor);
        }

        if (precioSinIvaStr != null && !precioSinIvaStr.isEmpty()) {
            Float precioSinIva = Float.parseFloat(precioSinIvaStr);
            producto.setPrecioSinIva(precioSinIva);
            producto.setPrecioConIva(calcularPrecioConIva(precioSinIva));
        }

        productoRepository.actualizar(producto);

        return producto;
    }


    public boolean eliminarProducto(String id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto == null) {
            return false;
        }
        productoRepository.eliminar(producto);
        return true;
    }
}
