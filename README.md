# Proyecto Mi Negocio

Ejercicio Practico en Spring Boot

````markdown
# 📌 Proyecto: API de Pacientes

API REST construida con Spring Boot que permite gestionar pacientes, citas médicas y profesionales de salud.

## 📦 Características

- CRUD de clientes y direcciones
- Validación para creación y axtualizacion de clientes
  - Validación de identificacion => longitud y si la identificación ya existe
  - Validación de tamaño de telefono
- Manejo global de errores (ExceptionHandler)
- Base de datos relacional con JPA/Hibernate => Postgres

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
````

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
