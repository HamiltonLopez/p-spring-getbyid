# Servicio de Obtención de Estudiante por ID

Microservicio Spring Boot para obtener un estudiante específico por su ID.

## Estructura del Proyecto

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/studentsgetbyid/
│   │   │       ├── controller/    # Controladores REST
│   │   │       ├── model/         # Entidades
│   │   │       ├── repository/    # Repositorios JPA
│   │   │       ├── service/       # Lógica de negocio
│   │   │       └── StudentGetByIdApplication.java
│   │   └── resources/
│   │       └── application.yml    # Configuración
│   └── test/                      # Pruebas unitarias
├── k8s/                          # Manifiestos de Kubernetes
│   ├── deployment.yaml
│   └── service.yaml
├── Dockerfile                    # Configuración de Docker
├── docker-compose.yml           # Configuración de Docker Compose
├── pom.xml                      # Dependencias Maven
└── README.md
```

## Endpoint

- **GET** `/api/students/{id}`
- Puerto: 8083
- Content-Type: application/json

### Path Parameters
- `id`: ID del estudiante (Long)

### Response

- **200 OK**
  ```json
  {
    "id": 1,
    "name": "string",
    "email": "string",
    "age": integer
  }
  ```
- **404 Not Found**: Si el estudiante no existe
- **500 Internal Server Error**: Error del servidor

## Configuración

### Application Properties
```yaml
server:
  port: 8083

spring:
  application:
    name: students-getbyid-service
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:mariadb://localhost:3306/studentsdb}
    username: root
    password: root
```

### Docker
```bash
# Construir imagen
docker build -t students-getbyid-service .

# Ejecutar contenedor
docker run -p 8083:8083 students-getbyid-service
```

### Docker Compose
```bash
docker compose up --build
```

### Kubernetes
```bash
kubectl apply -f k8s/
```

## Dependencias Principales

- Spring Boot 3.1.5
- Spring Data JPA
- MariaDB Driver
- Spring Web
- Spring Boot Test

## Desarrollo

### Requisitos
- Java 17
- Maven
- Docker (opcional)
- Kubernetes (opcional)

### Compilar
```bash
./mvnw clean package
```

### Ejecutar Tests
```bash
./mvnw test
```

### Ejecutar Localmente
```bash
./mvnw spring-boot:run
```

## Ejemplo de Uso

### Obtener un Estudiante por ID
```bash
curl http://localhost:8083/api/students/1
```

## Características

- Validación de ID existente
- Manejo de errores para IDs no encontrados
- Operación de solo lectura
- Respuesta detallada en caso de error

## Monitoreo y Logs

- Los logs de la aplicación se encuentran en la salida estándar
- Se utiliza el nivel de log INFO por defecto
- Se registran todas las consultas realizadas
- Se registran los intentos de acceso a IDs no existentes

## Rendimiento

- Consulta optimizada por clave primaria
- Caché no implementado (podría agregarse si es necesario)
- Tiempo de respuesta promedio: < 50ms 