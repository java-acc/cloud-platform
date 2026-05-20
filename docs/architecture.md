# byc-cloud-platform — Architecture

## Logical view

```
                   ┌─────────────────────┐
        client ──▶ │   gateway-service   │ ─ Sentinel ─ Nacos
                   └──────┬──────────────┘
                          │ lb://service-id
            ┌─────────────┼─────────────┐
            ▼             ▼             ▼
       user-service  order-service  rag-service (AI)
            │             │             │
            └───── Nacos config / discovery ─────┘
                          │
                ┌─────────┼─────────┐
                ▼         ▼         ▼
              MySQL     Redis    PGVector
```

## Module taxonomy

| Layer   | Examples                                                       | Where       |
|---------|----------------------------------------------------------------|-------------|
| Edge    | `gateway-service`                                              | `gateway/`  |
| Service | `user-service`, `order-service`                                | `services/` |
| Job     | `sync-job`                                                     | `jobs/`     |
| AI      | `rag-service`                                                  | `ai/`       |
| Library | `common-core`, `common-web`, `common-security`, `common-redis` | `common/`   |

## Cross-cutting concerns

- **Tracing** — every request gets `X-Trace-Id` at the gateway and is propagated downstream via
  `common-web/TraceFilter`.
- **Errors** — `GlobalExceptionHandler` in `common-web` maps `BizException` → `R.fail(code, msg)`.
- **Auth** — `common-security` provides JWT issuance & validation (HS256, 24h default TTL).
- **Caching** — `common-redis` exposes a Jackson-serialized `RedisTemplate<String, Object>`.

## Adding a module

```
sca add-service   user-service
sca add-job       sync-job
sca add-ai-service rag-service
sca add-common    common-mq
```

The CLI maintains:

- `pom.xml` (`<modules>`)
- `gateway-service/src/main/resources/application.yml` (`spring.cloud.gateway.routes`)
- `deploy/k8s/<service>.yaml`
- `deploy/helm/values.yaml` (`services.<name>`)
- `.github/workflows/ci.yml` (`jobs.build.strategy.matrix.module`)
