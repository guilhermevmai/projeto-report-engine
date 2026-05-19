# Agents

## Stack

- Spring Boot 3.5.14, Java 25, Maven wrapper (mvnw), WAR packaging
- Lombok (annotation processor, optional dep — excluded from final artifact)
- JMH 1.37 for microbenchmarks (`jmh-generator-annprocess` scope: provided)
- Jackson CSV + jackson-datatype-jsr310
- Tomcat embedded (also for external WAR deploy via `ServletInitializer`)

## Commands

| Action | Command |
|--------|---------|
| Compile | `.\mvnw compile` |
| Run tests | `.\mvnw test` |
| Dev server | `.\mvnw spring-boot:run` |
| Package WAR | `.\mvnw clean package` |
| AI helper | `.\mvnw exec:exec@run-ai` (runs `npx better-clawd` injecting `.env`) |

Always use `.\mvnw` (wrapper), never plain `mvn`.

## Project structure

```
src/
  main/java/com/report_engine/api/
    ApiApplication.java              — @SpringBootApplication + @EnableAsync entrypoint
    ServletInitializer.java          — WAR deploy support
    config/                          — WebMvcConfigurer (String→Enum converter)
    controller/                      — ReportController, BenchmarkController, TaskStatusController (skeleton)
    dto/                             — Sealed records: Success, Error, Warning, Async responses
    exceptions/                      — GenericException + GlobalControllerExceptionHandler
    factory/                         — ResponseFactory for building standardized responses
    infrastructure/                  — TaskTracker (skeleton)
    model/                           — UsersReport model + enums (ReadFilesStrategies, TaskState, UserStatus)
    service/                         — ReportService (CSV processing), BenchmarkService (JMH)
  main/resources/
    application.yaml                 — multipart 100MB, actuator (health/info/metrics)
    data/teste_grande.csv            — ~531K line CSV fixture
    data/Gerar CSV com dados.txt     — PowerShell script to regenerate CSV
  test/java/com/report_engine/api/
    ApiApplicationTests.java         — @SpringBootTest context load
    service/ArquivoBenchmark.java    — JMH benchmark (avg time, ms)
```

## API endpoints

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/api/reports/upload?file=&strategy=ALL_LINES|STREAM` | Upload & process CSV |
| POST | `/api/benchmarks/run-upload?strategy=...` | Run JMH benchmark (async) |
| GET | `/api/tasks/{id}` | Poll async task status (skeleton) |

Enum params (`ReadFilesStrategies`) auto-converted via global `WebMvcConfigurer` converter.

## Key conventions

- **Responses**: sealed records (`ApiResponse` permits `SucessResponse`, `ErrorResponse`, `WarningResponse`, `AsyncResponse`) built via `ResponseFactory`
- **Error handling**: `@RestControllerAdvice` in `GlobalControllerExceptionHandler`
- **Async processing**: `@EnableAsync` at app level, `TaskTracker` component tracks async task state
- **CSV processing strategies**: `ALL_LINES` (Files.readAllLines) vs `STREAM` (try-with-resources stream + forEach) — demonstrates Heap memory impact on file I/O
- **No generated code, no migrations, no CI workflows**

## Docs

Architecture Decision Records at `docs/adr/`:
- `adr-001` — JMH adoption rationale
- `adr-002` — API response standardization (enum binding, global error handler, immutable DTOs)
