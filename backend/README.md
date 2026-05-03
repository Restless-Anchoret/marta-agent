# Marta — Backend

Kotlin/Spring backend service that serves as the core of the Marta agent. It exposes a REST API consumed by the frontend, and owns the orchestration layer that drives conversations with Claude, manages tool execution, and handles personal assistance tasks.

## Stack

- **Language:** Kotlin 2.2.0
- **Framework:** Spring Boot 3.5.3
- **Build:** Gradle 9.5.0
- **JDK:** Java 25
- **Deployment:** Fly.io

## API

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/health` | Health check — returns `{"status":"ok"}` |

## Commands

**Run the application:**
```bash
./gradlew bootRun
```

**Run tests:**
```bash
./gradlew test
```

**Build (compile + test + package):**
```bash
./gradlew build
```

The application starts on port `8080` by default.
