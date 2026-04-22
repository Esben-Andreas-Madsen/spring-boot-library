# Spring Boot Library Application

The purpose of this project is to learn new technologies while keeping code quality high, aiming for a real production environment.  

### Frontend

- Jakarta EE with JSF Primefaces
- Keycloak integrated
- Wildfly deployment

### Backend

- Spring Boot REST API, pageable with filter and sorting
- Keycloak integrated
- PostgreSQL + Hibernate + JPA Specifications
- Swagger/OpenAPI documentation enabled

### DevOps and quality

- Fully containerized with Docker
- Test-automation with Github Action

## Details

### Running the Project
Requires Docker Desktop with [*.docker.internal resolving enabled](https://docs.docker.com/desktop/setup/install/windows-permission-requirements/#privileged-helper)  
Build and start all services:
`docker-compose up --build`  

## Services
### Spring Backend API
API URL: `http://localhost:8080`  
Connects to the `spring_boot_library_db` database  
Uses OAuth2 Authorization Code flow with Keycloak  

#### API Endpoints

OpenAPI Swagger is enabled at `http://localhost:8080/swagger-ui.html`  
Connect to the realm client first using ´spring-app´ as client id.  
Use credentials `user` and `password` to authorize OAuth2.  
There are endpoints for books, genres and authors.  

### Keycloak
URL: `http://localhost:1852`  
Admin username: `admin`  
Admin password: `admin`  
Runs in development mode with realm import enabled  
Imported user credentials:  
Username: `user`  
Password: `password`  

#### Authentication Configuration
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

Ran into a docker compose problem where Spring would only access the issuer uri internally, which redirected to keycloak externally which wasn't a resolvable hostname.
Got around it by using host.docker.internal for now.
