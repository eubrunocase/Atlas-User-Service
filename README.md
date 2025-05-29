# Atlas User Service

## Visão Geral

O Atlas User Service é um microserviço responsável pelo gerenciamento de informações de usuários na plataforma Atlas. Ele oferece operações CRUD (Create, Read, Update, Delete) para perfis de usuário, além de funcionalidades de busca e paginação.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.4.5**
* **Spring Cloud 2024.0.1**
* **Spring Cloud Netflix Eureka Client**
* **Spring Data JPA**
* **Hibernate**
* **Banco de Dados H2 (default) ou PostgreSQL**
* **Lombok**
* **SpringDoc OpenAPI (Swagger UI)**
* **Maven Wrapper**
* **Docker & Docker Compose**

## Pré-requisitos

* **Java 21+**
* **Maven 3.8+**
* **Docker & Docker Compose** (opcional)
* **Servidor Eureka** em execução (ex.: `http://localhost:8761`)

## Instalação e Execução

### Executando localmente

1. Clone o repositório:

   ```bash
   git clone <URL_DO_REPO>
   cd Atlas-User-Service
   ```
2. Configure variáveis de ambiente (opcionais):

   ```bash
   export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/userdb
   export SPRING_DATASOURCE_USERNAME=usuario
   export SPRING_DATASOURCE_PASSWORD=senha
   export EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka
   ```
3. Build e execução:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

### Usando Docker

1. Build da imagem:

   ```bash
   docker build -t atlas-user-service .
   ```
2. Executar o container:

   ```bash
   docker run -d --name atlas-user-service \
     -p 8082:8082 \
     -e SPRING_DATASOURCE_URL=jdbc:h2:mem:userdb \
     -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://host.docker.internal:8761/eureka \
     atlas-user-service
   ```

## Estrutura de Pastas

```text
Atlas-User-Service/
├── src/
│   ├── main/
│   │   ├── java/com/atlas/user
│   │   │   ├── AtlasUserServiceApplication.java
│   │   │   ├── config/
│   │   │   │   └── EurekaConfig.java
│   │   │   ├── controller/
│   │   │   │   └── UserController.java
│   │   │   ├── dto/
│   │   │   │   └── UserDTO.java
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java
│   │   │   └── service/
│   │   │       └── UserService.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/atlas/user/AtlasUserServiceTests.java
├── Dockerfile
├── pom.xml
├── mvnw, mvnw.cmd
└── HELP.md
```

## Configuração (application.yml)

```yaml
server:
  port: 8082

spring:
  application:
    name: atlas-user-service
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:h2:mem:userdb}
    username: ${SPRING_DATASOURCE_USERNAME:sa}
    password: ${SPRING_DATASOURCE_PASSWORD:}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: "${EUREKA_CLIENT_SERVICEURL_DEFAULTZONE:http://localhost:8761/eureka}"

logging:
  level:
    com.atlas.user: DEBUG
```

## Endpoints Principais

| Método | URL                                 | Descrição                               |
| ------ | ----------------------------------- | --------------------------------------- |
| POST   | `/atlas/users`                      | Cria um novo usuário                    |
| GET    | `/atlas/users`                      | Lista todos os usuários (com paginação) |
| GET    | `/atlas/users/{id}`                 | Retorna dados de um usuário específico  |
| PUT    | `/atlas/users/{id}`                 | Atualiza dados de um usuário            |
| DELETE | `/atlas/users/{id}`                 | Exclui um usuário                       |

## Documentação da API

A documentação interativa está disponível em:

* `http://localhost:8082/swagger-ui.html`
* `http://localhost:8082/swagger-ui/index.html`

