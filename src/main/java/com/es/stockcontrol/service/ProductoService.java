package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProductoRepository;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * The type Producto service.
 */
public class ProductoService {

    private ProductoRepository productoRepository;

    /**
     * Instantiates a new Producto service.
     *
     * @param productoRepository the producto repository
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Agregar producto producto.
     *
     * @param idProducto          the id producto
     * @param nombreProducto      the nombre producto
     * @param precioSinIva        the precio sin iva
     * @param descripcionProducto the descripcion producto
     * @param nombreProveedor     the nombre proveedor
     * @param direccionProveedor  the direccion proveedor
     * @param nombreCategoria     the nombre categoria
     * @param ivaRate             the iva rate
     * @return the producto
     */
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

        if (nombreProveedor == null || nombreProveedor.isEmpty() || direccionProveedor == null || direccionProveedor.isEmpty()) {
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

    /**
     * Baja producto boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean bajaProducto(String id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            productoRepository.eliminar(producto);
            return true;
        }
        return false;
    }

    /**
     * Modificar nombre producto boolean.
     *
     * @param id          the id
     * @param nuevoNombre the nuevo nombre
     * @return the boolean
     */
    public boolean modificarNombreProducto(String id, String nuevoNombre) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            producto.setNombre(nuevoNombre);
            productoRepository.actualizar(producto);
            return true;
        }
        return false;
    }

    /**
     * Modificar stock producto.
     *
     * @param id         the id
     * @param nuevoStock the nuevo stock
     */
    public void modificarStockProducto(String id, int nuevoStock) {
        productoRepository.modificarStock(id, nuevoStock);
    }

    /**
     * Gets producto.
     *
     * @param id the id
     * @return the producto
     */
    public Producto getProducto(String id) {
        Producto producto = productoRepository.buscarPorId(id);
        /*if (producto == null) {
            //System.out.println("ERROR CONTROLADO: Producto no encontrado.");
            return null;
        }*/
        return producto;
    }

    /**
     * Gets productos con stock.
     *
     * @return the productos con stock
     */
    public List<Producto> getProductosConStock() {
        return productoRepository.getProductosConStock();
    }

    /**
     * Gets productos sin stock.
     *
     * @return the productos sin stock
     */
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
