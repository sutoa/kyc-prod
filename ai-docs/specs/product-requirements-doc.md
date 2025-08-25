# KYC Prod — PRD (v0.1 + UI Search, Liquibase)

## 1) Project Overview
**Goal:** Web app (UI + REST API) to maintain KYC attributes for users—initially **Adoption Location** and **COE Region**.  
**v0.1 scope:** 
- **Backend:** Implement **one read-only REST API** that returns a user’s KYC info **by email**.  
- **Frontend:** Implement a **UI Search** page that lets a user enter an email and view the returned KYC info (read-only).

**Tech**
- **Backend:** Spring Boot 3 (Java 21), Maven, Spring Web, JPA/Hibernate, Validation, springdoc-openapi.
- **DB:** Oracle (prod), H2 (dev). **Schema migrations via Liquibase.**
- **Frontend:** React (latest stable) + TypeScript + Vite.
- **Deploy:** Container image; Kubernetes on **GKE**.
- **Docs:** Swagger UI for the REST API.

---

## 2) Roles (for future scopes)
- **Manager**, **User**. (No role gating in v0.1; add JWT/RBAC later.)

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

**Liquibase changelog** — `src/main/resources/db/changelog/db.changelog-master.xml`:
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

> Liquibase will map generic SQL types to Oracle/H2 automatically; adjust with `<modifyDataType>` if needed later.

---

## 4) REST API (v0.1)

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

## 5) UI (v0.1) — **Search Page**
**Route:** `/search` (default route)

**Features**
- Email input (required), basic client-side validation (format, length).
- Search button triggers `GET /api/v1/users/by-email?email=...`.
- States: **idle**, **loading**, **result**, **not found**, **error**.
- Result card shows **Full Name, Email, Adoption Location, COE Region, Updated At**.
- Utility actions: **Clear** form, copy email to clipboard.
- Accessibility: labeled input, keyboard navigable, screen-reader friendly messages.
- Responsive layout (single-column on mobile).

**Wireframe placeholder:** `/docs/wireframes/search.png`

**Example empty/result/error messages**
- Empty: “Enter an email to search.”
- Not Found: “No user found for that email.”
- Error: “Something went wrong. Please try again.”

---

## 6) Acceptance Criteria
- UI validates email format before calling the API; invalid email never triggers request.
- On valid email with existing record, UI shows result card with fields populated.
- On unknown email, UI shows “No user found” state.
- On backend/network error, UI shows a non-technical error message and allows retry.
- Swagger lists the endpoint; local dev works against H2 with Liquibase dev seed.

---

## 7) Non-Functional (v0.1)
- JSON logging; Spring Actuator health (dev).
- Container builds and runs locally; provide Kubernetes manifest stubs (deployment, service, readiness/liveness) for GKE.
- Minimal rate limiting not required; debounce client input optional.
- Internationalization not required in v0.1.

---

## 8) Cursor Tasks

**Backend scaffold**
> Create a Spring Boot 3 (Java 21, Maven) project `kyc-api` with:  
> - `spring-boot-starter-web`, `spring-boot-starter-validation`, `spring-boot-starter-data-jpa`  
> - **`liquibase-core`**, `org.springdoc:springdoc-openapi-starter-webmvc-ui`  
> - Dev DB: `com.h2database:h2`  
> - Prod DB: `com.oracle.database.jdbc:ojdbc11`  
> Add `UserProfile` entity + JPA repository.  
> Add Liquibase master changelog with changesets above.  
> Implement **GET `/api/v1/users/by-email?email=...`** controller + service.  
> Add global exception handler returning the error JSON format.  
> Enable Swagger UI at `/swagger-ui.html` in dev.

**Frontend scaffold**
> Create React + TypeScript + Vite app `kyc-web`. Add a `/search` page with an email input and a search button. On submit, call the API and render the states (idle/loading/result/not-found/error). Keep styles simple (CSS modules or Tailwind). Expose `VITE_API_BASE_URL` in `.env` and use it in a small API client. Add basic unit test for the search component (optional).

**Config examples**
- `application.yaml` (shared Liquibase reference):
  ```yaml
  spring:
    liquibase:
      change-log: classpath:db/changelog/db.changelog-master.xml
  ```
- `application-dev.yaml`: H2 config, ddl-auto=validate, Liquibase enabled, dev context for seed if desired.
- `application-prod.yaml`: Oracle URL/creds via env vars; ddl-auto=validate; Liquibase enabled.

**Dev run & seed**
- Add Liquibase changeSet `002-seed-user` with `context="dev"` for testing.

**Sample cURL**
```bash
curl "http://localhost:8080/api/v1/users/by-email?email=jane.smith@example.com"
```

---

## 9) Future Scopes (not in v0.1)
- Full search (by name/filters), create/edit, manager approve/reject, immutable version history, audit logs, JWT/RBAC, additional UI screens (Detail, Manager Queue, Version History).
