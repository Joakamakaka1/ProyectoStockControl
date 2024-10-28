package com.es.stockcontrol.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * The type Producto.
 */
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    private String id;
    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio_sin_iva", nullable = false)
    private Float precioSinIva;
    @Column(name = "precio_con_iva", nullable = false)
    private Float precioConIva;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    /**
     * Instantiates a new Producto.
     */
    public Producto() {
    }

    /**
     * Instantiates a new Producto.
     *
     * @param categoria    the categoria
     * @param nombre       the nombre
     * @param descripcion  the descripcion
     * @param precioSinIva the precio sin iva
     * @param precioConIva the precio con iva
     * @param fechaAlta    the fecha alta
     * @param proveedor    the proveedor
     */
    public Producto(String categoria, String nombre, String descripcion, Float precioSinIva, Float precioConIva, Date fechaAlta, Proveedor proveedor) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioSinIva = precioSinIva;
        this.precioConIva = precioConIva;
        this.fechaAlta = fechaAlta;
        this.stock = 0;
        this.proveedor = proveedor;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets categoria.
     *
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets categoria.
     *
     * @param categoria the categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets precio sin iva.
     *
     * @return the precio sin iva
     */
    public Float getPrecioSinIva() {
        return precioSinIva;
    }

    /**
     * Sets precio sin iva.
     *
     * @param precioSinIva the precio sin iva
     */
    public void setPrecioSinIva(Float precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    /**
     * Gets precio con iva.
     *
     * @return the precio con iva
     */
    public Float getPrecioConIva() {
        return precioConIva;
    }

    /**
     * Sets precio con iva.
     *
     * @param precioConIva the precio con iva
     */
    public void setPrecioConIva(Float precioConIva) {
        this.precioConIva = precioConIva;
    }

    /**
     * Gets fecha alta.
     *
     * @return the fecha alta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets fecha alta.
     *
     * @param fechaAlta the fecha alta
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets proveedor.
     *
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Sets proveedor.
     *
     * @param proveedor the proveedor
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Producto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", precioSinIva=").append(precioSinIva);
        sb.append(", precioConIva=").append(precioConIva);
        sb.append(", fechaAlta=").append(fechaAlta);
        sb.append(", stock=").append(stock);
        sb.append(", proveedorId=").append(proveedor != null ? proveedor.getId() : "N/A");
        sb.append('}');
        return sb.toString();
    }
}
