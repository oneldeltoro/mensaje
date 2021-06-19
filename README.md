# Microservicios para la gestion de tareas

- Estado del microservicio

  curl --request GET 'http://localhost:7000/tarea/heartbeat'

- Almacenar una tarea

  curl --location --request PUT 'http://localhost:7000/tarea/add' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "descripcion": "esto es probando",
  "fechaCreacio": "2021-05-07T20:39:43.994+00:00",
  "identificador": 11,
  "vigente": true
  }'

- Listado de tareas

  curl --request GET 'http://localhost:7000/tarea/all'

- Obtener una tarea
  
  curl --request GET 'http://localhost:7000/tarea/{id}'

- Eliminar una tarea 
  
  curl --request DELETE 'http://localhost:7000/tarea/{id}'

- Actualizar una tarea

  curl --location --request PUT 'http://localhost:7000/tarea/update' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "descripcion": "esto es probando",
   "fechaCreacio": "2021-05-07T20:39:43.994+00:00",
   "identificador": 11,
   "vigente": true
    }'
  
La configuración por defecto para la base de datos, es la siguiente:

```yaml
spring.datasource.url=jdbc:h2:mem:tareas #Ubicacion de la Tabla Tareas
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa #Nombre del usuario
spring.datasource.password=   #Password del Usuario
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true # Enabling H2 Console
spring.h2.console.path=/h2 # Custom H2 Console URL

## Hibernate Propiedades
jpa.hibernate.ddl-auto: update # Hibernate ddl auto (create, create-drop, validate, update)
jpa.properties.hibernate.dialect: org.hibernate.dialect.H2Dialect # The SQL dialect makes Hibernate generate better SQL for the chosen database
jpa.generate-ddl: true

```

Dentro de los valores , seleccionando formato `YAML` esta el puerto:

```yaml
server:
  port: 7000
```


