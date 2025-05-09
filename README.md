# 📌 Proyecto: Mi Negocio

El sistema Mi Negocio permite registrar facturas eficientemente para todos los usuarios, ya sean consumidores finales o clientes registrados. En la actualidad, se pretende optimizar el proceso de facturación a través de la incorporación de una buena administración de clientes.

## ✨ Mejoras propuestas

- Cada empresa que usa el sistema debe contar con su propia base de clientes.
- Al momento de facturar, se debe poder buscar clientes por nombre o número de identificación.

## 🎯 Funcionalidades requeridas

- Registrar nuevos clientes.
- Editar, eliminar y buscar clientes existentes.
- Permitir que cada cliente tenga múltiples direcciones (por ejemplo, sucursales).
- Registrar una única dirección matriz por cliente (no puede haber más de una).
- Durante la facturación, se debe poder:
  - Seleccionar otra dirección distinta a la matriz.
  - Registrar una nueva dirección en ese mismo momento si es necesario.

## 📦 Características

- CRUD de clientes y direcciones
- Validación para creación y actualizacion de clientes
  - Validación de identificacion => longitud y si la identificación ya existe
  - Validación de longitud de telefono
- Manejo global de errores (ExceptionHandler)
- Base de datos relacional con JPA/Hibernate => Postgres

## ▶️ Cómo ejecutar el proyecto

### 🔧 Requisitos previos

- Java 17
- Maven 3.8+
- PostgreSQL
- (Opcional) Postman para probar la API

### 🛠 Clonar el proyecto

```bash
git clone https://github.com/ShootDomy/MiNegocio.git
cd minegocio
```

### 🧹 Compilar y construir el proyecto

```bash
mvn clean install
```

### ⚙️ Configurar variables de entorno

Asegúrese de que el archivo application.properties contenga la configuración correcta para la base de datos:

```properties
spring.application.name=minegocio

# ===============================
# DATASOURCE CONFIGURATION
# ===============================
spring.datasource.url=jdbc:postgresql://localhost:5432/alquimia
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

# ===============================
# JPA / HIBERNATE
# ===============================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql=TRACE

# ===============================
# LIQUIBASE
# ===============================
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:/db/changelog/liquibase-changelog.xml
spring.liquibase.contexts=dev
spring.liquibase.default-schema=public

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### ⚙️ Validaciones extra

Configurar UK para validacion de una dirección matriz por cliente

```sql
CREATE UNIQUE INDEX uk_direccion_matriz
ON direccion (cli_id)
WHERE dir_matriz = true;
```

### ▶️ Ejecutar el proyecto

```bash
mvn spring-boot:run
```

## 🛠 Endpoints principales

### CLIENTE

| Método | Ruta           | Descripción         |
| ------ | -------------- | ------------------- |
| POST   | `/api/cliente` | Crear nuevo cliente |

**Body:**

```json
{
  "cliTipoIdentificacion": "CEDULA",
  "cliNumIdentificacion": "0987456324",
  "cliNombre": "Maria Rosal",
  "cliCorreo": "maria.rosal@example.com",
  "cliTelefono": "097456324",
  "direcciones": [
    {
      "dirProvincia": "Pichincha",
      "dirCiudad": "Quito",
      "dirdireccion": "Av. Amazonas y Naciones Unidas",
      "dirMatriz": true
    },
    {
      "dirProvincia": "Guayas",
      "dirCiudad": "Guayaquil",
      "dirdireccion": "Malecón 2000",
      "dirMatriz": false
    }
  ]
}
```

**RESPONSE:**

```json
{
  "cliId": 11,
  "cliTipoIdentificacion": "CEDULA",
  "cliNumIdentificacion": "0987456321",
  "cliNombre": "Maria Rosal",
  "cliCorreo": "maria.rosal@example.com",
  "cliTelefono": "097456324",
  "direcciones": [
    {
      "dirId": 18,
      "dirProvincia": "Pichincha",
      "dirCiudad": "Quito",
      "dirdireccion": "Av. Amazonas y Naciones Unidas",
      "dirMatriz": true,
      "cliId": 11
    },
    {
      "dirId": 19,
      "dirProvincia": "Guayas",
      "dirCiudad": "Guayaquil",
      "dirdireccion": "Malecón 2000",
      "dirMatriz": false,
      "cliId": 11
    }
  ]
}
```

| Método | Ruta                   | Descripción                    |
| ------ | ---------------------- | ------------------------------ |
| PATCH  | `/api/cliente/{cliId}` | Actualizar cliente por `cliId` |

**Body:**

```json
{
  "cliTipoIdentificacion": "CEDULA",
  "cliNumIdentificacion": "0987456321",
  "cliNombre": "Maria Rosal NEW",
  "cliCorreo": "maria.rosal@example.com",
  "cliTelefono": "097456324",
  "direcciones": [
    {
      "dirProvincia": "Pichincha",
      "dirCiudad": "Quito",
      "dirdireccion": "Av. Amazonas y Naciones Unidas",
      "dirMatriz": true
    },
    {
      "dirProvincia": "Guayas",
      "dirCiudad": "Guayaquil",
      "dirdireccion": "Malecón 2000",
      "dirMatriz": false
    }
  ]
}
```

**Response:**

```json
{
  "cliId": 11,
  "cliTipoIdentificacion": "CEDULA",
  "cliNumIdentificacion": "0987456321",
  "cliNombre": "Maria Rosal NEW",
  "cliCorreo": "maria.rosal@example.com",
  "cliTelefono": "097456324",
  "direcciones": [
    {
      "dirId": 18,
      "dirProvincia": "Pichincha",
      "dirCiudad": "Quito",
      "dirdireccion": "Av. Amazonas y Naciones Unidas",
      "dirMatriz": true,
      "cliId": 11
    },
    {
      "dirId": 19,
      "dirProvincia": "Guayas",
      "dirCiudad": "Guayaquil",
      "dirdireccion": "Malecón 2000",
      "dirMatriz": false,
      "cliId": 11
    }
  ]
}
```

| Método | Ruta                           | Descripción                  |
| ------ | ------------------------------ | ---------------------------- |
| GET    | `/api/cliente`                 | Obtener todos los clientes   |
| GET    | `/api/cliente/{cliId}`         | Obtener cliente por `cliId`  |
| GET    | `/api/cliente/buscar/{buscar}` | Buscar cliente por parámetro |
| DELETE | `/api/cliente/{cliId}`         | Eliminar cliente por `cliId` |

### DIRECCION

| Método | Ruta             | Descripción           |
| ------ | ---------------- | --------------------- |
| POST   | `/api/direccion` | Crear nueva dirección |

**Body:**

```json
{
  "dirProvincia": "PICHINCHA",
  "dirCiudad": "QUITO 1",
  "dirdireccion": "ASD",
  "dirMatriz": true,
  "cliId": 2
}
```

**Response:**

```json
{
  "dirId": 1,
  "dirProvincia": "PICHINCHA",
  "dirCiudad": "QUITO 1",
  "dirdireccion": "ASD",
  "dirMatriz": true,
  "cliId": 2
}
```

| Método | Ruta                     | Descripción                      |
| ------ | ------------------------ | -------------------------------- |
| PATCH  | `/api/direccion/{dirId}` | Actualizar dirección por `dirId` |

**Body:**

```json
{
  "dirProvincia": "PICHINCHA",
  "dirCiudad": "QUITO",
  "dirdireccion": "QUITO NORTE PATCH",
  "dirMatriz": false,
  "cliId": 6
}
```

**Response:**

```json
{
  "dirId": 4,
  "dirProvincia": "PICHINCHA",
  "dirCiudad": "QUITO",
  "dirdireccion": "QUITO NORTE PATCH",
  "dirMatriz": false,
  "cliId": 6
}
```

| Método | Ruta                             | Descripción                    |
| ------ | -------------------------------- | ------------------------------ |
| GET    | `/api/direccion`                 | Obtener todas las direcciones  |
| GET    | `/api/direccion/{dirId}`         | Obtener dirección por `dirId`  |
| GET    | `/api/direccion/buscar/{buscar}` | Buscar dirección por parámetro |
| DELETE | `/api/direccion/{dirId}`         | Eliminar dirección por `dirId` |

## 🧪 Colección de Postman

Puede importar esta colección en Postman para probar los endpoints fácilmente:

📁 [Descargar colección](./src/Postman/Mi-Negocio.postman_collection.json)

> Esta colección incluye todos los endpoints documentados de la API de clientes y direcciones.

### 📥 ¿Cómo importar en Postman?

1. Abrir Postman.
2. Hacer clic en `Import`.
3. Seleccionar el archivo `./src/Postman/Mi-Negocio.postman_collection.json`.
4. ¡Listo! Ya se puede ejecutar los endpoints.

## 📊 Resultados

A continuación se muestra un video demostrando el funcionamiento y resultados del sistema:

🎥 [Ver video en YouTube](https://www.youtube.com/watch?v=YUwqr4Bw6p0)

## 👨‍💻 Información del Autor

Este proyecto fue creado y mantenido por **Domenica Vintimilla**.

- **Correo**: [canizaresdomenica4@gmail.com](mailto:canizaresdomenica4@gmail.com)
- **GitHub**: [https://github.com/ShootDomy](https://github.com/ShootDomy)
- **LinkedIn**: [https://www.linkedin.com/in/domenica-vintimilla-24a735245/](https://www.linkedin.com/in/domenica-vintimilla-24a735245/)
