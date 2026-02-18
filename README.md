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

### Stack
Backend: Spring Boot (`port 8080`)  
Application Database: PostgreSQL 17 (`port 5432`)  
Authentication Server: Keycloak 26.4 (`port 1852`)  
Keycloak Database: PostgreSQL 17  
Containerization: Dockerfile + Docker Compose  

### Running the Project
Build and start all services:
`docker-compose up --build`

### Services
#### Backend 
API URL: `http://localhost:8080`  
Connects to the `spring_boot_library_db` database  
Uses OAuth2 Authorization Code flow with Keycloak  

#### Keycloak
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

