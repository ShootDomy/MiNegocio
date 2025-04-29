# Proyecto Mi Negocio

Ejercicio Practico en Spring Boot

## 🛠 Endpoints principales

### CLIENTE

| Método | Ruta                   | Descripción                    |
| ------ | ---------------------- | ------------------------------ |
| POST   | `/api/cliente`         | Crear nuevo cliente            |
| PATCH  | `/api/cliente/{cliId}` | Actualizar cliente por `cliId` |

```json
[
  {
    "dirId": 1,
    "calle": "Av. Siempre Viva",
    "numero": "742",
    "ciudad": "Springfield"
  },
  {
    "dirId": 2,
    "calle": "Calle Falsa",
    "numero": "123",
    "ciudad": "Shelbyville"
  }
]

| Método | Ruta                                 | Descripción                         |
|--------|--------------------------------------|-------------------------------------|
| GET    | `/api/cliente`                       | Obtener todos los clientes          |
| GET    | `/api/cliente/{cliId}`               | Obtener cliente por `cliId`         |
| GET    | `/api/cliente/buscar/{buscar}`       | Buscar cliente por parámetro        |
| DELETE | `/api/cliente/{cliId}`               | Eliminar cliente por `cliId`        |

### DIRECCION
| Método | Ruta                                  | Descripción                          |
|--------|---------------------------------------|--------------------------------------|
| GET    | `/api/direccion`                      | Obtener todas las direcciones        |
| GET    | `/api/direccion/{dirId}`              | Obtener dirección por `dirId`        |
| GET    | `/api/direccion/buscar/{buscar}`      | Buscar dirección por parámetro       |
| POST   | `/api/direccion`                      | Crear nueva dirección                |
| PATCH  | `/api/direccion/{dirId}`              | Actualizar dirección por `dirId`     |
| DELETE | `/api/direccion/{dirId}`              | Eliminar dirección por `dirId`       |
```
