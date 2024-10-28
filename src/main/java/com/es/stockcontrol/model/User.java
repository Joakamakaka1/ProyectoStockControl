package com.es.stockcontrol.model;

import jakarta.persistence.*;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    private String nombre_usuario;
    @Column (name = "nombre", length = 20, nullable = false)
    private String password;

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User.
     *
     * @param nombre_usuario the nombre usuario
     * @param password       the password
     */
    public User(String nombre_usuario, String password) {
        this.nombre_usuario = nombre_usuario;
        this.password = password;
    }

    /**
     * Gets nombre usuario.
     *
     * @return the nombre usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * Sets nombre usuario.
     *
     * @param nombre_usuario the nombre usuario
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
