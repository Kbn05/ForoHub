# ForoHub

![Badge en Desarollo](https://img.shields.io/badge/status-done-green)
![Java](https://img.shields.io/badge/Java-21-blue.svg)
![GitHub last commit](https://img.shields.io/github/last-commit/Kbn05/AluraChallenge.svg)

ForoHub es una aplicación de foro en línea que permite a los usuarios crear y gestionar temas de discusión. Los usuarios pueden registrarse, iniciar sesión y participar en conversaciones sobre diversos cursos.

## Características

- Registro y autenticación de usuarios.
- Creación, actualización y eliminación de temas de discusión.
- Asociación de temas con cursos específicos.
- Roles de usuario con permisos personalizados (ADMIN, USER).

## Tecnologías Utilizadas

- Java 11
- Spring Boot
- Spring Security
- JPA/Hibernate
- PostgreSQL
- Maven

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- `model`: Contiene las entidades JPA.
- `repository`: Contiene las interfaces de repositorio para interactuar con la base de datos.
- `service`: Contiene la lógica de negocio.
- `controller`: Contiene los controladores REST.
- `utils`: Contiene utilidades y clases auxiliares.

## Requisitos 

- Java 11
- PostgreSQL
- Maven

## Endpoints

### Usuarios
- POST /users/register: Registra un nuevo usuario.
- POST /users/login: Inicia sesión.
### Topics
- GET /topics: Obtiene todos los temas.
- POST /topics: Crea un nuevo tema.
- PUT /topics/{id}: Actualiza un tema existente.
- DELETE /topics/{id}: Elimina un tema existente.
