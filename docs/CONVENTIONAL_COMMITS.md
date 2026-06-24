
| Type | English Description | 中文释义 |
| :--- | :--- | :--- |
| `feat` | A new feature | 引入新功能 |
| `fix` | A bug fix | 修复 Bug |
| `docs` | Documentation only changes | 仅文档变更（如修改 README.md、API 说明文档等） |
| `style` | Code style changes that do not affect meaning (formatting, whitespace, etc.) | 代码格式调整（不影响逻辑，如空格、缩进、格式化、缺失分号等） |
| `refactor` | Code changes that neither fix a bug nor add a feature | 代码重构（既不修复 Bug 也不添加新功能的代码结构调整） |
| `wip` | Work in progress | 开发中/进行中（未完成的临时提交） |
| `chore` | Other maintenance tasks that don't modify src or tests | 日常杂务/琐事（不修改源代码或测试文件的琐碎变更，如更新 .gitignore） |
| `test` | Adding or correcting tests | 测试用例变更（添加缺失的测试或修改、增加现有测试） |
| `perf` | Performance improvements | 性能优化（提高代码运行效率或减少资源占用） |
| `build` | Changes affecting the build system or external dependencies | 构建系统或外部依赖变更（如修改 Webpack、Vite、Vite、Maven 配置等） |
| `ci` | Changes to CI/CD configuration and scripts | 持续集成（CI/CD）配置及脚本变更（如 GitHub Actions、GitLab CI） |
| `revert` | Revert a previous commit | 代码回滚（撤销/恢复先前的某次提交） |
| `hotfix` | Urgent production fix | 紧急线上修复（通常指生产环境的紧急热修复） |
| `merge` | Merge branches or pull requests | 合并分支或 PR |
| `release` | Release preparation or version bump | 发布新版本 / 修改版本号 / 打标签 |
| `ui` | User interface changes | UI 界面修改（如样式调整、页面排版修改） |
| `config` | Configuration changes | 配置文件修改（如修改应用层 config 文件） |
| `deps` | Dependency updates | 依赖升级或调整 |
| `cleanup` | Code cleanup without functional changes | 代码清理（无功能变更的死代码、冗余代码清理） |
| `init` | Initial project setup | 项目初始化（通常仅用于仓库创建时的第一次提交） |
| `db` | Database schema or migration changes | 数据库结构或迁移脚本修改 |
| `api` | API interface changes | API 接口修改 |
| `ux` | User experience improvements | 用户体验优化 |
| `log` | Logging improvements or adjustments | 日志相关修改（添加、删除或调整日志打印） |
| `security` | Security fixes or enhancements | 安全修复或安全增强 |
| `i18n` | Internationalization and localization changes | 国际化/本地化修改 |
| `assets` | Static resource updates | 静态资源修改（如图片、字体、多媒体文件更新） |
| `sync` | Synchronize code or resources | 同步代码或资源 |
| `rename` | Rename files, variables, or modules | 重命名（文件、变量或模块名调整） |
| `move` | File or module relocation | 文件或模块目录移动 |
| `remove` | Remove obsolete code or resources | 删除废弃代码、无用文件或资源 |
| `upgrade` | Upgrade framework or major components | 升级核心框架或大版本组件 |
| `downgrade` | Downgrade dependencies or versions | 降级依赖或版本 |
| `data` | Data updates or seed data changes | 业务数据或初始化种子数据修改 |
| `monitor` | Monitoring and observability changes | 监控、埋点、可观测性相关修改 |
| `infra` | Infrastructure changes | 基础设施变更（如 Dockerfile、K8s 配置调整） |
| `ops` | Operations and deployment changes | 运维与部署相关修改 |
| `lint` | Lint rule changes or fixes | Lint 语法校验规则调整 |
| `experiment` | Experimental changes | 实验性功能尝试 |
| `prototype` | Prototype implementation | 原型功能实现 |

```
# GitToolBox commit message regex
(?:feat|fix|docs|style|refactor|wip|chore|test|perf|build|ci|revert|hotfix|merge|release|ui|config|deps|cleanup|init|db|api|ux|log|security|i18n|assets|sync|rename|move|remove|upgrade|downgrade|data|monitor|infra|ops|lint|experiment|prototype)(?:\(.*\))?: [a-zA-Z].*\s#\d+
```