# Creación de Clases de Modelo en Hibernate

Este documento explica cómo crear clases de modelo en una API usando Hibernate. Usaremos ejemplos básicos para ilustrar cada concepto clave.

## Introducción a las Clases de Modelo

Las clases de modelo representan las entidades que se almacenan en la base de datos. Cada clase se convierte en una tabla, y cada atributo de la clase se convierte en una columna de esa tabla. Usaremos anotaciones de JPA (Jakarta Persistence API) para definir cómo se mapean estas clases.

## Pasos para Crear una Clase de Modelo

### 1. Definir la Clase

Cada clase debe ser marcada como una entidad utilizando la anotación `@Entity`.

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    // Atributos de la clase
}

```

### 2.  Definir Atributos

Los atributos de la clase representan las columnas de la tabla. Deben ser privados y puedes usar la anotación @Column para definir propiedades adicionales, como si el campo puede ser nulo o su longitud.

```java
import jakarta.persistence.Column;

public class NombreDeLaClaseModel {
    @Column(name = "categoria", nullable = false, length = 10)
    private String categoria;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    // Más atributos...
}
```

### 3. Definir la Clave Primaria

Para definir la clave primaria, utiliza la anotación @Id. Cada entidad debe tener un campo único que sirva como identificador.

```java
import jakarta.persistence.Id;

public class NombreDeLaClaseModel {
    @Id
    private String id;

    // Otros atributos...
}
```

### 4. Manejo de Relaciones

Si tu modelo tiene relaciones entre entidades, utiliza anotaciones como @ManyToOne, @OneToMany, etc. Para establecer una relación, usa @JoinColumn para definir cómo se relacionan las tablas.

```java
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@ManyToOne
@JoinColumn(name = "id_nombreDeLaClase")
private NombreDeLaClaseModel nombreDeLaClase;
```

### 5. Constructores

Es importante incluir al menos un constructor vacío (requerido por Hibernate) y un constructor que inicialice todos los atributos.

```java
public NombreDeLaClaseModel() {
    // Constructor vacío
}

public NombreDeLaClaseModel(String id, String categoria, String nombre) {
    this.id = id;
    this.categoria = categoria;
    this.nombre = nombre;
}
```

### 6. Getters y Setters

Proporciona métodos get y set para cada atributo. Esto es importante para el acceso controlado a los datos.

```java
public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}
```

