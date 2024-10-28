package com.es.stockcontrol.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * The type Proveedor.
 */
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

    /**
     * Instantiates a new Proveedor.
     */
    public Proveedor() {
    }

    /**
     * Instantiates a new Proveedor.
     *
     * @param nombre    the nombre
     * @param direccion the direccion
     */
    public Proveedor(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets direccion.
     *
     * @param direccion the direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets productos.
     *
     * @return the productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Sets productos.
     *
     * @param productos the productos
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Proveedor{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", direccion='").append(direccion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
