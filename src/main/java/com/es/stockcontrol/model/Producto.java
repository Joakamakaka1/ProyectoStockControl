package com.es.stockcontrol.model;

import jakarta.persistence.*;

import java.util.Date;

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

    public Producto() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(Float precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public Float getPrecioConIva() {
        return precioConIva;
    }

    public void setPrecioConIva(Float precioConIva) {
        this.precioConIva = precioConIva;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getStock() {
        return stock;
    }

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
        // Incluimos solo el id del proveedor
        sb.append(", proveedorId=").append(proveedor != null ? proveedor.getId() : "N/A");
        sb.append('}');
        return sb.toString();
    }
}
