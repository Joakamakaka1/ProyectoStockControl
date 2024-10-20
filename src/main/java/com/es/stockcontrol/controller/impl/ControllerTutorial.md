# Creación de la Capa de Controlador en Hibernate

Este documento explica cómo crear la capa de controlador en una API usando Hibernate y JPA. Los controladores son
responsables de manejar las solicitudes HTTP, interactuando con la capa de servicio para aplicar la lógica de negocio y
gestionar las respuestas a los clientes.

## Introducción a la Capa de Controlador

En una arquitectura de tres capas, la **capa de controlador** es la encargada de recibir las solicitudes HTTP del
cliente (como solicitudes **POST**, **GET**, **PUT** y **DELETE**), delegar las operaciones a la capa de servicio, y
retornar las respuestas correspondientes. El controlador es el punto de entrada de la aplicación para la interacción con
el cliente.

### Responsabilidades de la Capa de Controlador:

1. **Delegar la lógica de negocio** a la capa de servicio, manteniendo el controlador libre de lógica compleja.
2. **Validar la entrada del usuario** antes de delegar las operaciones al servicio.
3. **Retornar respuestas HTTP** adecuadas, como códigos de estado y mensajes, según el resultado de la operación.

## Pasos para Crear una Clase de Controlador

### 1. Definir la Clase


```java
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

public class NombreDeLaClaseController {
    private final NombreDeLaClaseService nombreDeLaClaseService;

    public NombreDeLaClaseController(NombreDeLaClaseService nombreDeLaClaseService) {
        this.nombreDeLaClase = nombreDeLaClase;
    }

    // Métodos que gestionan las solicitudes HTTP...
}
```

### 2. Realizar los metodos CRUD necesarios

```java
@Override
public RespuestaHTTP<NombreDeLaClaseModel> altaProducto(String idProducto,String nombreProducto,String precioSinIva,String descripcionProducto,String nombreProveedor,String direccionProveedor){
        return null;
}

@Override
public RespuestaHTTP<NombreDeLaClaseModel> bajaProducto(String id){
        return null;
}

@Override
public RespuestaHTTP<NombreDeLaClaseModel> modificarNombreProducto(String id,String nuevoNombre){
        return null;
}

@Override
public RespuestaHTTP<NombreDeLaClaseModel> modificarStockProducto(String id,String nuevoStock){
        return null;
}

@Override
public RespuestaHTTP<NombreDeLaClaseModel> getProducto(String id){
        return null;
}

@Override
public RespuestaHTTP<List<NombreDeLaClaseModel>>getProductosConStock(){
        return null;
}

@Override
public RespuestaHTTP<List<NombreDeLaClaseModel>>getProductosSinStock(){
        return null;
}
```