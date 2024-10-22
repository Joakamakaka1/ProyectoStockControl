package com.es.stockcontrol.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column (name = "direccion", nullable = false)
    private String direccion;
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<Producto> productos;

    public Proveedor() {
    }

    public Proveedor(Long id, String nombre, String direccion, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
