# API de Gestión de Productos y Categorías

Este es un proyecto de ejemplo de una API RESTful desarrollada con Spring Boot para gestionar productos y categorías. Permite realizar operaciones CRUD completas para ambas entidades.

## Tecnologías Utilizadas

*   **Java 17**
*   **Spring Boot 3**
*   **Spring Data JPA** (para la persistencia de datos)
*   **H2 Database** (base de datos en memoria para desarrollo)
*   **Lombok** (para reducir código repetitivo)
*   **SpringDoc OpenAPI** (para la documentación automática de la API)

## Cómo Ejecutar el Proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd curso-introduccion-apis
    ```

2.  **Ejecutar la aplicación:**
    Puedes ejecutar la aplicación usando el wrapper de Maven incluido:
    ```bash
    ./mvnw spring-boot:run
    ```
    O importando el proyecto en tu IDE preferido (IntelliJ, Eclipse, etc.) y ejecutando la clase principal `Application.java`.

3.  **La API estará disponible en:** `http://localhost:8080`

## Documentación de la API (Swagger UI)

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API a través de Swagger UI en la siguiente URL:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Desde esta interfaz podrás ver todos los endpoints, sus descripciones, los parámetros que aceptan y probarlos directamente.

También puedes obtener la definición OpenAPI en formato JSON desde:

[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Este es el archivo que puedes importar en herramientas como Postman.
