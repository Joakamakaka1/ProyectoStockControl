markdown
Copiar código
# Creación de Repositorios en Hibernate

Este documento explica cómo crear clases de repositorio en una API usando Hibernate y JPA para gestionar la persistencia de entidades en la base de datos. Usaremos ejemplos básicos para ilustrar cada concepto clave.

## Introducción a los Repositorios

Los repositorios son clases que actúan como una capa de abstracción para interactuar con la base de datos. Estas clases permiten realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre las entidades de una manera más organizada y separada de la lógica del negocio.

## Pasos para Crear un Repositorio

### 1. Definir la Clase

Crea una clase que implemente las operaciones CRUD básicas. Este repositorio se encargará de manejar las entidades correspondientes en la base de datos. En este ejemplo, `ProductoRepository` maneja la entidad `Producto`.

```java
import com.es.stockcontrol.model.NombreDeLaClase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class NombreDeLaClaseRepository implements BaseRepository<NombreDeLaClaseModel, String> {
    private EntityManager entityManager;

    public NombreDeLaClaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
```

### 2. Metodo guardar (POST)

Este método es responsable de guardar una nueva instancia de la entidad Producto en la base de datos.

```java
@Override
public void guardar(NombreDeLaClaseModel nombreDeLaClase) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(nombreDeLaClase);
    transaction.commit();
}

```
### 3. Metodo buscarPorId (GET)

Este método busca una entidad Producto en la base de datos usando su clave primaria (ID).

```java
@Override
public NombreDeLaClaseModel buscarPorId(String id) {
    return entityManager.find(NombreDeLaClase.class, id);
}

```

### 4. Método actualizar (PUT)

Este método actualiza una entidad existente en la base de datos utilizando entityManager.merge.

```java
@Override
public void actualizar(NombreDeLaClaseModel nombreDeLaClase) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.merge(nombreDeLaClase);
    transaction.commit();
}

```

### 5. Método borrar (DELETE)

Este método elimina una entidad Producto de la base de datos.

```java
@Override
public void eliminar(NombreDeLaClaseModel nombreDeLaClase) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.remove(nombreDeLaClase);
    transaction.commit();
}

```