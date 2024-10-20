# Creación de la Capa de Servicio en Hibernate

Este documento explica cómo crear una capa de servicio en una API usando Hibernate y JPA para gestionar la lógica de negocio de la aplicación. La capa de servicio actúa como un puente entre los controladores y los repositorios, implementando la lógica que gobierna las operaciones sobre las entidades.

## Introducción a la Capa de Servicio

En una arquitectura de tres capas, la **capa de servicio** es responsable de encapsular la **lógica de negocio**. Esta capa se encarga de recibir datos de la capa de **controlador**, aplicar reglas y validaciones, e interactuar con la capa de **repositorio** para acceder o modificar la información almacenada en la base de datos.

### Responsabilidades de la Capa de Servicio:
1. **Implementar la lógica de negocio** que define el comportamiento de la aplicación.
2. **Interactuar con el repositorio** para realizar las operaciones CRUD, asegurándose de aplicar las reglas de negocio antes de modificar los datos.
3. **Validar datos y aplicar cálculos** específicos, como calcular precios con IVA o generar identificadores únicos para las entidades.
4. **Centralizar la lógica de negocio**, permitiendo que los controladores interactúen de manera sencilla con los datos.

## Pasos para Crear una Clase de Servicio

### 1. Definir la Clase

Cada servicio está asociado a una entidad y debe contener la lógica de negocio que le corresponde. Por ejemplo, un servicio para la entidad `Producto` podría implementar la lógica de creación y cálculo del precio con IVA.

```java
import com.es.stockcontrol.repository.NombreDeLaClaseRepository;
import com.es.stockcontrol.model.NombreDeLaClase;

public class NombreDeLaClaseService {

    private final nombreDeLaClaseRepository nombreDeLaClaseRepository;

    @Inject
    public NombreDeLaClaseService(NombreDeLaClaseService nombreDeLaClaseService) {
        this.nombreDeLaClaseService = nombreDeLaClaseService;
    }
    
    // Lógica de negocio, operaciones CRUD...
}
```
### 2. Interactuar con el Repositorio

El servicio debe interactuar con el repositorio para realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar). La capa de servicio no realiza consultas directas, sino que delega estas tareas a la capa de repositorio.

### 3. Lógica de Negocio 

La capa de servicio debe aplicar las reglas de negocio, como generar el ID del producto o calcular el precio con IVA. En este ejemplo, la generación del ID sigue la regla de negocio que combina las primeras tres letras de la categoría, nombre y proveedor.

### 4. Validaciones y Cálculos

La capa de servicio también puede validar los datos antes de interactuar con el repositorio. Por ejemplo, se puede validar que los campos obligatorios no sean nulos o que el nombre y la categoría cumplan con las restricciones de longitud.
