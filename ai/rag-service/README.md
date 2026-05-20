# rag-service

rag-service module

|              |                  |
|--------------|------------------|
| Port         | `8100`           |
| Gateway path | `/api/ai/rag/**` |
| LLM provider | `openai`         |
| Vector store | `pgvector`       |
| Package      | `com.byc.ai.rag` |

## Endpoints

```bash
# Ingest a document
curl -X POST http://localhost:8100/api/ai/rag/ingest \
  -H 'Content-Type: application/json' \
  -d '{"text": "Spring Cloud Alibaba is..."}'

# Ask
curl -X POST http://localhost:8100/api/ai/rag/ask \
  -H 'Content-Type: application/json' \
  -d '{"question": "What is Spring Cloud Alibaba?"}'
```

## Environment

| Var                  | Default                     |
|----------------------|-----------------------------|
| `OPENAI_API_KEY`     | (required)                  |
| `OPENAI_BASE_URL`    | `https://api.openai.com/v1` |
| `OPENAI_CHAT_MODEL`  | `gpt-4o-mini`               |
| `OPENAI_EMBED_MODEL` | `text-embedding-3-small`    |
