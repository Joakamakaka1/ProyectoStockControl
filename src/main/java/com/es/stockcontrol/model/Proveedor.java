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
    @OneToMany(mappedBy = "proveedor")
    List<Producto> productos; //Relacion de <OneToMany>
}
