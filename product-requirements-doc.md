# KYC Prod — PRD (v0.1 Minimal, Liquibase)

## 1) Project Overview
**Goal:** Web app (UI + REST API) to maintain KYC attributes for users—initially **Adoption Location** and **COE Region**.  
**v0.1 scope:** Implement **one read-only REST API** that returns a user’s KYC info **by email**. (Write-only/edit/approval/versioning will come later.)

**Tech**
- **Backend:** Spring Boot 3 (Java 21), Maven, Spring Web, JPA/Hibernate, Validation, springdoc-openapi.
- **DB:** Oracle (prod), H2 (dev). **Schema migrations via Liquibase.**
- **Frontend:** React (latest stable) + TypeScript (placeholder for read-only screen later).
- **Deploy:** Container image; Kubernetes on **GKE**.
- **Docs:** Swagger UI for the REST API.

---

## 2) Roles (for future scopes)
- **Manager**, **User**. (No role gating needed for the single read endpoint in v0.1; add JWT/RBAC later.)

---

## 3) Data Model (minimum viable)

**UserProfile**
- `id` (UUID / VARCHAR2(36))
- `fullName` (STRING)
- `email` (STRING, **unique**, used as lookup key)
- `adoptionLocation` (STRING)
- `coeRegion` (STRING)
- `createdAt` (timestamp)
- `updatedAt` (timestamp)

**Liquibase changelog** (XML) — `src/main/resources/db/changelog/db.changelog-master.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.23.xsd">

    <changeSet id="001-create-user-profiles" author="kyc">
        <createTable tableName="user_profiles">
            <column name="id" type="varchar(36)">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="full_name" type="varchar(256)">
                <constraints nullable="false"/>
            </column>
            <column name="email" type="varchar(256)">
                <constraints nullable="false" unique="true"/>
            </column>
            <column name="adoption_location" type="varchar(128)"/>
            <column name="coe_region" type="varchar(128)"/>
            <column name="created_at" type="timestamp" defaultValueComputed="CURRENT_TIMESTAMP">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="timestamp" defaultValueComputed="CURRENT_TIMESTAMP">
                <constraints nullable="false"/>
            </column>
        </createTable>

        <createIndex tableName="user_profiles" indexName="idx_user_profiles_email">
            <column name="email"/>
        </createIndex>
    </changeSet>

    <!-- Optional seed for dev -->
    <changeSet id="002-seed-user" author="kyc" context="dev">
        <insert tableName="user_profiles">
            <column name="id" value="11111111-1111-1111-1111-111111111111"/>
            <column name="full_name" value="Jane Smith"/>
            <column name="email" value="jane.smith@example.com"/>
            <column name="adoption_location" value="Austin, TX"/>
            <column name="coe_region" value="NA-COE"/>
        </insert>
    </changeSet>
</databaseChangeLog>
```

> Liquibase will map generic SQL types to Oracle/H2 automatically. If needed, add `<modifyDataType>` changes per DB in later versions.

---

## 4) REST API (the only one in v0.1)

### Get user by **email**
- **Method/Path:** `GET /api/v1/users/by-email`
- **Query Parameter (required):** `email=<user@domain>`
- **Purpose:** Return the **current** KYC info for that user.
- **Auth:** None in dev (add JWT later).
- **Responses:**
  - `200 OK`
    ```json
    {
      "fullName": "Jane Smith",
      "email": "jane.smith@example.com",
      "adoptionLocation": "Austin, TX",
      "coeRegion": "NA-COE",
      "updatedAt": "2025-08-10T12:34:56Z"
    }
    ```
  - `404 Not Found` if email not present.
  - `400 Bad Request` if `email` missing/invalid.

**Error format:**
```json
{ "timestamp":"...", "status":404, "error":"Not Found", "message":"User not found: jane.smith@example.com", "path":"/api/v1/users/by-email" }
```

**Swagger/OpenAPI:** springdoc; Swagger UI at `/swagger-ui.html` in dev.

---

## 5) Acceptance Criteria
- Given an existing email, `GET /api/v1/users/by-email?email=...` returns **200** with correct values.
- Unknown email returns **404**.
- Swagger lists the endpoint with schema examples.
- Works with **H2 (dev)** and **Oracle (prod)** using **Liquibase** migrations.

---

## 6) Non-Functional (v0.1)
- JSON logging; Spring Actuator health (dev).
- Container builds and runs locally; provide Kubernetes manifest stubs (deployment, service, readiness/liveness) for GKE.
- Minimal validation on the `email` parameter (not blank, reasonable length, basic format).

---

## 7) Placeholders
- **Wireframes:** `/docs/wireframes/read-user.png` (to be supplied later).

---

## 8) Cursor Tasks

**Backend scaffold**
> Create a Spring Boot 3 (Java 21, Maven) project `kyc-api` with:  
> - `spring-boot-starter-web`, `spring-boot-starter-validation`, `spring-boot-starter-data-jpa`  
> - **`liquibase-core`**, `org.springdoc:springdoc-openapi-starter-webmvc-ui`  
> - Dev DB: `com.h2database:h2`  
> - Prod DB: `com.oracle.database.jdbc:ojdbc11`  
> Add `UserProfile` entity + JPA repository.  
> Add `src/main/resources/db/changelog/db.changelog-master.xml` (see PRD).  
> Wire Liquibase to run on startup for both dev and prod.  
> Implement **GET `/api/v1/users/by-email?email=...`** in controller + service.  
> Add global exception handler to return the error JSON format above.  
> Enable Swagger UI at `/swagger-ui.html` in dev.

**Config examples**
- `application.yaml` (shared):
  ```yaml
  spring:
    liquibase:
      change-log: classpath:db/changelog/db.changelog-master.xml
  ```
- `application-dev.yaml`: H2 file/in-memory, Hibernate ddl-auto=validate, Liquibase enabled, `spring.profiles.active: dev`.
- `application-prod.yaml`: Oracle URL/creds via env vars; ddl-auto=validate; Liquibase enabled; set Liquibase context if you want to skip seeds in prod.

**Dev run & seed**
- Use Liquibase `context="dev"` for sample seed data (changeSet `002-seed-user`).

**Sample cURL**
```bash
curl "http://localhost:8080/api/v1/users/by-email?email=jane.smith@example.com"
```

---

## 9) Future Scopes (not in v0.1)
- Search, create/edit, manager approve/reject, immutable version history, audit logs, JWT/RBAC, UI screens (Search, Detail, Manager Queue, Version History).
