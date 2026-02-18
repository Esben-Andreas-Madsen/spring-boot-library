# Spring Boot Library Application

Practicing Spring Boot by making a library project with:
- Books & Authors
- REST API (may or may not use hypermedia mappers)
- Postgres DB
- docker-compose option
- minikube option
- (possibly a frontend)
- oauth2 authentication

## Description

### Spring Boot Library – Docker Setup
Dockerized development environment for a Spring Boot backend secured with OAuth2 (Keycloak) and backed by PostgreSQL.

### Running the Project
Build and start all services:
`docker-compose up --build`  

## Services
### Spring Backend API
API URL: `http://localhost:8080`  
Connects to the `spring_boot_library_db` database  
Uses OAuth2 Authorization Code flow with Keycloak  
Use credentials `user` and `password` to authorize OAuth2

#### API Endpoints
#### Books
| HTTP Method | Endpoint           | Description            | Request Body           | Response Type     |
|------------|--------------------|------------------------|------------------------|------------------|
| GET        | /api/books         | Get all books          | —                      | List<BookDto>    |
| GET        | /api/books/{id}    | Get book by ID         | —                      | BookDto          |
| POST       | /api/books         | Create new book        | CreateBookRequest      | BookDto          |
| PUT        | /api/books/{id}    | Update existing book   | UpdateBookRequest      | BookDto          |
| DELETE     | /api/books/{id}    | Delete book by ID      | —                      | void (204/200)   |

#### Authors
| HTTP Method | Endpoint            | Description              | Request Body            | Response Type       |
|------------|---------------------|--------------------------|-------------------------|--------------------|
| GET        | /api/authors        | Get all authors          | —                       | List<AuthorDto>    |
| GET        | /api/authors/{id}   | Get author by ID         | —                       | AuthorDto          |
| POST       | /api/authors        | Create new author        | CreateAuthorRequest     | AuthorDto          |
| PUT        | /api/authors/{id}   | Update existing author   | UpdateAuthorRequest     | AuthorDto          |
| DELETE     | /api/authors/{id}   | Delete author by ID      | —                       | void (204/200)     |


### Keycloak
URL: `http://localhost:1852`  
Admin username: `admin`  
Admin password: `admin`  
Runs in development mode with realm import enabled  
Imported user credentials:  
Username: `user`  
Password: `password`  

### Authentication Configuration
Client ID: `spring-app`  
Grant type: `authorization_code`  
Scope: `openid`  
Redirect URI: `{baseUrl}/login/oauth2/code/{registrationId}`  

### Data Persistence
Docker volumes are used for database persistence:  
`postgres_data` – Application database  
`keycloak_postgres_data` – Keycloak database  
Data remains available after container restarts.  

### Notes
Keycloak runs in development mode (start-dev)  
Hibernate `ddl-auto=update` is enabled  
Default credentials are for local development only  
Not production-ready without proper security hardening  

