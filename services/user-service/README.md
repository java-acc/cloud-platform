# user-service

user-service module

|              |                         |
|--------------|-------------------------|
| Port         | `8080`                  |
| Gateway path | `/api/user/**`          |
| Package      | `com.byc.user`          |
| Module path  | `services/user-service` |

## Run locally

```bash
mvn -pl services/user-service -am spring-boot:run
# Health
curl http://localhost:8080/api/user/ping
# Via gateway
curl http://localhost:8080/api/user/ping
```

## Layout

```
src/main/java/com/byc/user/
├── UserServiceApplication.java
├── controller/         REST endpoints
├── service/            business logic
├── repository/         persistence (MyBatis-Plus mapper)
├── domain/             entities / DTOs
└── feign/              outbound feign clients
```
