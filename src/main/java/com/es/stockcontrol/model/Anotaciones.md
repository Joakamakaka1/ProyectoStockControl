# Anotaciones Usadas en Hibernate

Este documento describe las anotaciones más comunes usadas en Hibernate (y JPA). Estas anotaciones permiten mapear clases Java a tablas en bases de datos relacionales, definir relaciones entre entidades y controlar el comportamiento de persistencia.

## Lista de Anotaciones

### 1. @Entity

Marca una clase como una entidad JPA que se mapeará a una tabla en la base de datos.

```java
import jakarta.persistence.Entity;

@Entity
public class Producto {
    // Atributos de la clase
}
```

### 2. @Table

Define el nombre de la tabla que se va a mapear a la clase. Por defecto, se utiliza el nombre de la clase.

```java
import jakarta.persistence.Table;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClase {
    // Atributos de la clase
}
```

### 3. @Id

Define el atributo como una clave primaria.

```java
import jakarta.persistence.Id;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClase {
    @Id
    public int id;
    // Atributos de la clase
}
```

### 4. @GeneratedValue

Define el generador de valores para la clave primaria. Por defecto, se utiliza el generador de valores de Hibernate.

```java
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClase {
    @Id
    @GeneratedValue
    public int id;
    // Atributos de la clase
}
```

### 5. @Column

Define las propiedades adicionales de una columna. Por defecto, se utiliza el nombre del atributo.  

```java
import jakarta.persistence.Column;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClase {
    @Id
    public int id;
    @Column
    public String nombre;
    // Atributos de la clase
}
```

### 6. @ManyToOne

Define una relación de uno a uno entre dos entidades. Por defecto, se utiliza el nombre del atributo.

```java
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClase {
    @Id
    public int id;
    @ManyToOne
    public NombreDeLaClase relacion;
    // Atributos de la clase
}
```

### 7. @OneToMany

Define una relacion de uno a muchos entre dos entidades. Por defecto, se utiliza el nombre del atributo.

```java
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClase {
    @Id
    public int id;
    @OneToMany
    public List<NombreDeLaClase> relacion;
    // Atributos de la clase
}
```

### 8. @ManyToMany

Define una relacion de muchos a muchos entre dos entidades. Por defecto, se utiliza el nombre del atributo.

```java
import jakarta.persistence.ManyToMany; 

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    public int id;
    @ManyToMany
    public List<NombreDeLaClaseModel> relacion;
    // Atributos de la clase
}
```

### 9. @OneToOne

Define una relacion de uno a uno entre dos entidades. Por defecto, se utiliza el nombre del atributo.

```java
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    public int id;
    @OneToOne
    public NombreDeLaClaseModel relacion;
    // Atributos de la clase
}
```

### 10. @JoinColumn

Define el nombre de la columna de la clave externa en la tabla relacionada. Por defecto, se utiliza el nombre del atributo.

```java
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    public int id;
    @ManyToOne
    @JoinColumn
    public NombreDeLaClaseModel relacion;
    // Atributos de la clase
}
```

### 11. @MappedBy

Define el nombre de la propiedad en la entidad relacionada que representa la clave externa. Por defecto, se utiliza el nombre del atributo.

```java
import jakarta.persistence.MappedBy;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    public int id;
    @ManyToOne
    @JoinColumn
    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    public NombreDeLaClaseModel relacion;
    // Atributos de la clase
}
```

### 12. @Transient

Indica que un campo no debe ser persistido en la base de datos. Hibernate lo ignorará en las operaciones de persistencia.

```java
import jakarta.persistence.Transient;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    public int id;
    @Transient
    public String nombre;
    // Atributos de la clase
}
```

### 13. @Cascade

Se utiliza para definir cómo se deben propagar las operaciones de persistencia entre entidades relacionadas, como PERSIST, MERGE, REMOVE, etc.

```java
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne
    @JoinColumn
    public NombreDeLaClaseModel relacion;
    // Atributos de la clase
}
```

### 14. @Fetch

Controla cómo se debe recuperar la relación, ya sea con FetchType.LAZY (carga bajo demanda) o FetchType.EAGER (carga inmediata).

```java
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nombre_de_la_tabla")
public class NombreDeLaClaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    public NombreDeLaClaseModel relacion;
    // Atributos de la clase
}
```




