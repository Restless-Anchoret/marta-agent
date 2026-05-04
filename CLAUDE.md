# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository structure

Monorepo with two independently deployed apps:
- `backend/` — Kotlin/Spring Boot REST API (deployed to Fly.io)
- `frontend/` — TypeScript/React web interface (deployed to Vercel)

All backend commands must be run from `backend/`.

## Backend commands

```bash
./gradlew bootRun        # start the app (port 8080)
./gradlew build          # compile + test + package
./gradlew compileKotlin  # compile only
./gradlew test           # run all tests
./gradlew test --tests "com.marta.agent.SomeTest"  # run a single test class
```

Requires `ANTHROPIC_API_KEY` env var to be set at runtime (read via `application.properties` as `claude.api-key`).

## Backend architecture

Spring Boot app with a clean three-layer structure under `com.marta.agent`:

- **`controller/`** — REST controllers (`@RestController`). Request/response data classes live in the same file as their controller.
- **`service/`** — Business logic (`@Service`). `AgentService` is the main orchestration entry point — it receives user messages and will drive Claude interactions.
- **`client/`** — External API clients (`@Component`). `ClaudeClient` wraps the Anthropic Java SDK; it holds the `AnthropicClient` instance and exposes simple string-in/string-out methods.

The intended data flow is: `MessageController` → `AgentService` → `ClaudeClient` → Anthropic API.

## Key technical constraints

- Dependency versions are extracted to `val` variables at the top of `build.gradle.kts`. Plugin versions inside the `plugins {}` block must stay as string literals (Gradle constraint).
- Use `@param:Value(...)` (not bare `@Value`) for Spring `@Value` annotations on constructor parameters — required to avoid a Kotlin compiler warning about annotation target ambiguity.
