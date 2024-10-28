package com.es.stockcontrol.model;

import java.util.Optional;

/**
 * Clase que almacena la respuesta que se da desde el Controller al Cliente (main)
 *
 * @param <T> : T puede ser un objeto de tipo User, o de tipo Producto, o ...
 */
public class RespuestaHTTP<T> {
    private int codigo;
    private String mensaje;
    private T obj;

    /**
     * Instantiates a new Respuesta http.
     *
     * @param codigo  the codigo
     * @param mensaje the mensaje
     * @param obj     the obj
     */
    public RespuestaHTTP(int codigo, String mensaje, T obj) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.obj = obj;
    }

    /**
     * Instantiates a new Respuesta http.
     */
    public RespuestaHTTP() {

    }

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Sets codigo.
     *
     * @param codigo the codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets mensaje.
     *
     * @param mensaje the mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets obj.
     *
     * @return the obj
     */
    public T getObj() {
        return obj;
    }

    /**
     * Sets obj.
     *
     * @param obj the obj
     */
    public void setObj(T obj) {
        this.obj = obj;
    }
}
