# sync-job

sync-job module

|                 |                    |
|-----------------|--------------------|
| Port (actuator) | `8090`             |
| Default cron    | `0 0/5 * * * ?`    |
| Package         | `com.byc.sync.job` |

## Override schedule

Via Nacos config:

```yaml
app:
  job:
    sync:
      cron: "0 0 2 * * ?"
```
