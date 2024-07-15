# API REST con Spring Boot

Este proyecto es una API REST desarrollada con Spring Boot, utilizando Java 17 y Maven. La API proporciona endpoints para la gestión de recursos y está diseñada para ser escalable y fácil de mantener.

## Configuración del proyecto

El proyecto fue creado utilizando Spring Initializr con las siguientes especificaciones:

- **Java:** versión 17 o superior
- **Maven:** versión 4 (utilizada por Spring Initializr)
- **Spring Boot**
- **Formato del proyecto:** JAR

## Dependencias

Las siguientes dependencias fueron agregadas al crear el proyecto:

- **Lombok:** Para reducir el código boilerplate en las clases Java.
- **Spring Web:** Para construir los endpoints RESTful.
- **Spring Boot DevTools:** Para facilitar el desarrollo con características como el reinicio automático.
- **Spring Data JPA:** Para la persistencia de datos utilizando JPA (Java Persistence API).
- **Flyway Migration:** Para la migración y versión de la base de datos.
- **MySQL Driver:** Para la conexión con la base de datos MySQL.
- **Validation:** Para la validación de datos en las solicitudes HTTP.
- **Spring Security:** Para la gestión de la seguridad en la API.

## Requisitos previos

Asegúrate de tener instalado lo siguiente:

- **JDK 17** o superior: Necesario para compilar y ejecutar la aplicación.
- **Maven 4.x**: Para la gestión de dependencias y construcción del proyecto.
- **MySQL**: Como base de datos para almacenar la información.
