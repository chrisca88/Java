# API Rest

### Resumen
API Rest de ventas con implementacion de SpringBoot con JPA.

### Estructura del Repositorio

- bin: Contiene el/los archivo/s Java
- src: codigo fuente de la APIRest
- postman scripts: Scripts de Postman


### Dependencias
- Spring Web
- Spring Data JPA
- Lombok
- MySql Driver

### Configuracion
#### Spring
- Maven
- Spring Boot version 3.2.2
- Java version: 21

#### MySQL
- Puerto: 3306


### Endpoints:
- http://localhost:8080/clientes/listar: Lista la tabla de clientes
- http://localhost:8080/clientes/listar/{id}: Lista un cliente especifico
- http://localhost:8080/clientes/alta: agrega un nuevo cliente
- http://localhost:8080/clientes/modificar/{id}: modifica un clientes especifico
- http://localhost:8080/clientes/baja/{id}: eliminar un cliente especifico
- http://localhost:8080/productos/listar: Lista todos los productos
- http://localhost:8080/productos/agregar: Agregar un nuevo producto
- http://localhost:8080/productos/cambiar/{id}: modificar un producto especifico
- http://localhost:8080/productos/borrar/{id}: eliminar un producto especifico
- http://localhost:8080/ventas/listar: Lista todas las ventas
- http://localhost:8080/ventas/crear: Genera una nueva venta

###Valores para probar:
#INSERT INTO cliente (id,apellido,nombre,nacimiento)
VALUES
(1,'Perez','Juan','05/10/1968'),
(2,'Gonzalez','Carla','14/05/2007'),
(3,'Rodriguez','Ramiro','05/01/1995');

#INSERT INTO Producto (id,descripcion,nombre,stock,precio)
VALUES
(1,'Funko pop Dragon Ball','Goku',15,17000),
(2,'Funko pop One Piece','Luffy',4,21000),
(3,'Funko pop Pokemon','Pikachu',7,15750);

###Generar una venta
{
  "clienteId": 2,
  "productos": [
    {
      "productoId": 1,
      "cantidad": 2
    },
    {
      "productoId": 2,
      "cantidad": 2
    }
  ]
}
