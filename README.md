# Spring Boot - API (Java 8)

_API Rest with Spring Boot, Java 8, JPA, MySql_


## Start Project

* **https://start.spring.io/**

```
Add Dependencies:

MySQL = Conecction BD
JPA = Mapping Data

```

## Run Project

```
mvnw.cmd spring-boot:run

OR

.\mvnw.cmd spring-boot:run

http://localhost:8080/

```

## Config Database
_Modificar el fichero application.properties y agregar las credenciales de conexión a la base de datos MySQL_

```
Path:

demo\src\main\resources\application.properties
```

## Accessing data with MySQL

* **https://spring.io/guides/gs/accessing-data-mysql/**

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
#spring.jpa.show-sql: true

```


## Mapeo de fechas con @Temporal

```
Se pueden establecer tres posibles valores para la anotación:

DATE: Acotara el campo solo a la Fecha, descartando la hora.
@Temporal(TemporalType.DATE)

TIME: Acotara el campo solo a la Hora, descartando a la fecha.
@Temporal(TemporalType.TIME)

TIMESTAMP: Toma la fecha y hora.
@Temporal(TemporalType.TIMESTAMP)


EXAMPLE:


package com.obb.jpa.jpaturorial.entity;

import java.util.Calendar;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author Oscar Blancarte
 */
@Entity
@Table(
    name = "EMPLOYEES" , 
    schema = "jpatutorial", 
    indexes = {@Index(name = "name_index", columnList = "name",unique = true)}
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EmployeeTable")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NAME", nullable = false, length = 150)
    private String name; 
    
    @Column(name = "SALARY", nullable = false, scale = 2)
    private double salary;
    
    @Column(name = "REGIST_DATE", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar registDate;
    
    @Column(name="STATUS", nullable = false, length = 8 )
    @Enumerated(value = EnumType.STRING)
    private Status status;
    
    /**
     * GETs and SETs
     */
}


```

## LocalDateTime to TIMESTAMP

```

  //spring way, with @EnableJpaAuditing on configuration
    @EntityListeners(AuditingEntityListener.class)
    class MyEntity {
        @CreatedDate
        @Column(columnDefinition = "TIMESTAMP", nullable = false)
        private LocalDateTime created_at;
    }

    //hibernate way
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    //mysql way
    @Column(insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime created_at;



Para el mysql la parte importante es: insertable=false Para mysql usando el DEFAULT CURRENT_TIMESTAMP Definido, y updatable=false Para ON UPDATE CURRENT_TIMESTAMP.

De lo contrario, el orm de hibernación enviaría created_at=null Como valor nulo a la base de datos, ¡evitando que la definición de columna se haga cargo! O peor, si establece nullable=false, La inserción fallará aunque definió el manejo predeterminado de la marca de tiempo para la columna.



Others:

`select UNIX_TIMESTAMP(TIME) date from table`



https://stackoverflow.com/questions/49954812/how-can-you-make-a-created-at-column-generate-the-creation-date-time-automatical


```

## SQL

```
El sentido a usar TimeStamp es para asegurarte de que dicha fecha se establezca automáticamente
cuando se inserte o actualice el registro.

Por ejemplo si quieres guardar la fecha y hora de un evento lo defines así:

CREATE TABLE `eventos` 
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Evento` VarChar(50) NOT NULL DEFAULT '', 
  `FechaHora` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
  PRIMARY KEY (`Id`)
);

INSERT INTO eventos(Evento) VALUES('Evento 0001');
SELECT * FROM eventos; 

Otro ejemplo con UPDATE:

CREATE TABLE `productos` 
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VarChar(50) NOT NULL DEFAULT '', 
  `Marca` VarChar(50) NOT NULL DEFAULT '', 
  `Referencia` VarChar(50) NOT NULL DEFAULT '', 
  `FechaModif` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP, 
  PRIMARY KEY (`Id`)
);

INSERT INTO productos (Nombre, Marca, Referencia, FechaModif) VALUES ('MOUSE', 'GENIUS', 'X105', NOW());
SELECT * FROM productos;  

```

## Many-To-Many Relationship in JPA

* **https://www.baeldung.com/jpa-many-to-many**
* **https://www.baeldung.com/spring-data-rest-relationships**
* **https://medium.com/codestorm/spring-boot-jpa-entity-relationships-526d46ae228e**
* **https://medium.com/huawei-developers/database-relationships-in-spring-data-jpa-8d7181f50f60**

```
- Implementation in JPA
- Working with Relationships in Spring Data REST
- Spring Boot — JPA Entity Relationships
- Database Relationships In Spring Data JPA

```

## MySQL CLI

```
mysql -u root -p
mysql -h localhost -u root -p

use mibasedatos;
show databases;


Otras Sentencias SQL:


create database miprueba;
use miprueba;

show tables;
describe administrador;

mysql> create table prueba (id_prueba int); 
Query OK, 0 rows affected (0.08 sec) 

mysql> insert into prueba (id_prueba) values (1); 
Query OK, 1 row affected (0.00 sec) 

mysql> insert into prueba (id_prueba) values (2); 
Query OK, 1 row affected (0.00 sec) 

mysql> insert into prueba (id_prueba) values (3); 
Query OK, 1 row affected (0.00 sec) 

mysql> select * from prueba;


Para salir de la línea de comandos de MySQL:

mysql> quit

```

## Aplicaciones web con Spring Boot capa a capa

* **https://www.adictosaltrabajo.com/2016/12/22/aplicaciones-web-con-spring-boot-capa-a-capa/**

```

@Controller: Con esta anotación Spring podrá detectar la clase SampleController cuando realice el escaneo de componentes.

@Autowired: A través de esta anotación Spring será capaz de llevar a cabo la inyección de dependencias sobre el atributo marcado. En este caso, estamos inyectando la capa de servicio, y por eso no tenemos que instanciarla.

@RequestMapping: Con esta anotación especificamos la ruta desde la que escuchará el servicio, y qué método le corresponde.

@ResponseBody: Con ella definimos lo que será el cuerpo de la respuesta del servicio.

@PathVariable: Sirve para indicar con qué variable de la url se relaciona el parámetro sobre el que se esté usando la anotación.

Podemos también utilizar la etiqueta @RestController en lugar de @Controller, que sustituye al uso de @Controller + @ResponseBody, quedando el controlador de la siguiente forma:


```
