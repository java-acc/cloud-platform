# 几米宠约 (Jimi Pet)
> 寓意：“几米”代表距离近（上门快），“约”代表预约服务，连接宠物服务的一切商家与需求。
> 主打基于地理位置的多租户服务，让附近的专业人士（邻居）来帮忙遛狗喂鸟。

| 业务   | 类型   | 重要程度  |
| ---- | ---- | ----- |
| 商户管理 | 支撑领域 | ★★★★  |
| 宠物主人 | 核心领域 | ★★★★★ |
| 宠物档案 | 核心领域 | ★★★★★ |
| 遛宠服务 | 核心领域 | ★★★★★ |
| 上门喂养 | 核心领域 | ★★★★★ |
| 宠物洗护 | 核心领域 | ★★★★  |
| 疫苗预约 | 核心领域 | ★★★★  |
| 宠物丧葬 | 核心领域 | ★★★   |
| 订单中心 | 核心领域 | ★★★★★ |
| 支付   | 支撑领域 | ★★★★★ |
| 通知   | 通用领域 | ★★★★  |
| 短信   | 防腐层  | ★★★★  |
| 文件存储 | 通用领域 | ★★★★  |
| 权限认证 | 通用领域 | ★★★★★ |
| 租户管理 | 通用领域 | ★★★★★ |

```
├─pet-service-iam(认证中心)
    商户员工/管理员/宠物主人/服务人员（遛狗师、护理师）
    OAuth2.1 + JWT + Spring Security 7
    User
    Role
    Permission
    Organization
├─pet-service-market(服务商品中心)
    遛宠/洗护/喂养/丧葬/疫苗
    enum ServiceType {
        WALKING,FEEDING,GROOMING,FUNERAL,VACCINATION
    }
    PetServiceProduct
     ├── PricePolicy
     ├── ScheduleRule
     └── ServiceTag
├─pet-service-merchant(商户上下文)
├─pet-service-notification(通知中心)
    短信/邮件/微信模板消息/APP Push/站内信
├─pet-service-order(订单中心)
    CREATE,PAYING,PAID,SERVING,FINISHED,CANCELLED,REFUNDING,REFUNDED
    Order
     ├── OrderItem
     ├── OrderSnapshot
     ├── PaymentInfo
     └── RefundInfo
├─pet-service-owner(宠物主人中心)
    PetOwner
     ├── Address
     ├── Membership
     └── EmergencyContact
├─pet-service-payment(支付中心)
    微信/支付宝/退款/分账/对账
    infrastructure
      └── acl
           ├── wechat
           ├── alipay
           └── unionpay
├─pet-service-pet(宠物中心)
    主人信息/地址/宠物关系/黑名单
    Pet
     ├── VaccineRecord
     ├── MedicalRecord
     ├── Breed
     └── Photo
├─pet-service-scheduling(预约中心)
    时间段/资源占用/排班/预约
    Appointment
     ├── TimeSlot
     ├── Resource
     └── Schedule
├─pet-service-tenant(租户中心)
    商户入驻/SaaS租户管理/租户套餐/租户配置/多租户隔离
    Tenant(聚合)
     ├── TenantConfig
     ├── TenantPackage
     └── TenantStatus
└─pet-service-worker(服务人员中心)
    遛狗师/护理员/丧葬人员/兽医
    Worker
     ├── Skill
     ├── Certification
     ├── WorkingArea
     └── Schedule
    ├─pet-service-worker-application
    ├─pet-service-worker-contract
    ├─pet-service-worker-domain
    ├─pet-service-worker-infrastructure
    ├─pet-service-worker-interfaces
    └─pet-service-worker-start
```