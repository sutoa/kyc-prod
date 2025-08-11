# KYC API Service

A Spring Boot service that manages KYC (Know Your Customer) attributes for users, specifically tracking Adoption Location and COE Region information.

## Features (v0.1)

- REST API to retrieve user KYC information by email
- Liquibase database migrations for schema versioning
- Support for both H2 (dev) and Oracle (prod) databases
- OpenAPI/Swagger documentation
- Health monitoring via Spring Actuator

## Tech Stack

- Java 21
- Spring Boot 3
- Maven
- Liquibase
- H2 Database (dev)
- Oracle Database (prod)
- SpringDoc OpenAPI

## Getting Started

### Prerequisites

- Java 21 JDK
- Maven
- Docker (optional, for containerization)

### Development Setup

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd kyc-prod
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run in development mode:
   ```bash
   mvn spring-boot:run -Dspring.profiles.active=dev
   ```

The application will start with the H2 database and sample data loaded.

### API Documentation

When running in dev mode, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

### Sample API Usage

Get user KYC info by email:
```bash
curl "http://localhost:8080/api/v1/users/by-email?email=jane.smith@example.com"
```

### Database Migrations

Database schema is managed through Liquibase. Migration files are located in:
```
src/main/resources/db/changelog/
```

### Configuration

- `application.yaml`: Shared configuration
- `application-dev.yaml`: Development profile with H2 database
- `application-prod.yaml`: Production profile with Oracle database

## Production Deployment

### Environment Variables

Required for production:
```
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//[host]:[port]/[service]
SPRING_DATASOURCE_USERNAME=[username]
SPRING_DATASOURCE_PASSWORD=[password]
```

### Running in Production

```bash
java -jar target/kyc-api-0.1.0.jar --spring.profiles.active=prod
```

## Contributing

1. Create a feature branch
2. Make your changes
3. Submit a pull request

## License

[License Information]


--------
## start backend
 mvn spring-boot:run -Dspring.profiles.active=dev

 ## start frontend
 cd kyc-ui
 npm run dev