# People API - Solución para Prueba Técnica Inditex Core Platform

## Descripción

API Spring Boot que resuelve el caso técnico propuesto por Inditex para la gestión de precios de productos según fechas, marcas y prioridades.

## Requisitos cumplidos

✅ Endpoint REST que consulta precios aplicables  
✅ Base de datos H2 en memoria con datos iniciales  
✅ Manejo de prioridades para desambiguar precios  
✅ Validación de parámetros y manejo de errores  
✅ Pruebas automatizadas para los 5 casos requeridos  
✅ Documentación clara del servicio  

## Tecnologías utilizadas

- Java 31
- Spring Boot 3.4.4
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 (para pruebas)

## Estructura del proyecto
```
src/
├── main/
│ ├── java/com/company/people/
│ │ ├── controller/PriceController.java # Endpoint REST
│ │ ├── entity/Price.java               # Modelo de datos
│ │ ├── repository/PriceRepository.java # Acceso a BD
│ │ ├── service/PriceService.java       # Lógica de negocio
│ │ └── PeopleApiApplication.java       # Clase principal
│ └── resources/
│ ├── application.properties            # Configuración
│ ├── data.sql                          # Datos iniciales (casos de prueba)
│ └── schema.sql                        # Esquema de BD
└── test/                               # Pruebas unitarias y de integración
```

## Cómo ejecutar

1. Clonar el repositorio
2. Actualizar Maven
3. Ejecutar como Aplicacion Java la clase PeopleApiApplication
   
La API estará disponible en http://localhost:8080

Endpoint principal
Consulta de precios
GET /price

Parámetros:

applicationDate: Fecha de aplicación (formato ISO: yyyy-MM-ddTHH:mm:ss)

productId: ID del producto (ej: 35455)

brandId: ID de la marca (ej: 1 para ZARA)

Ejemplo de llamada:

Copy
GET /price?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1
Respuesta exitosa (200 OK):

```
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

## Casos de prueba implementados
Los tests automatizados validan los 5 escenarios requeridos:

Test 1: 10:00 del 14/06 - Producto 35455, Marca 1 → Precio 35.50 €

Test 2: 16:00 del 14/06 - Producto 35455, Marca 1 → Precio 25.45 €

Test 3: 21:00 del 14/06 - Producto 35455, Marca 1 → Precio 35.50 €

Test 4: 10:00 del 15/06 - Producto 35455, Marca 1 → Precio 30.50 €

Test 5: 21:00 del 16/06 - Producto 35455, Marca 1 → Precio 38.95 €


## Base de datos H2
Consola H2: http://localhost:8080/h2-console

Credenciales:

JDBC URL: jdbc:h2:mem:pricingdb
User: sa
Password: password

La base de datos se inicializa automáticamente con los datos de prueba proporcionados en el enunciado.

Decisiones técnicas
Manejo de prioridades: La consulta SQL ordena por prioridad DESC y limita a 1 resultado

Formato fechas: Se usa ISO-8601 para consistencia

Validaciones: Se comprueba que existan precios para los parámetros dados

Testing: Cubre 100% de los casos requeridos más casos adicionales
