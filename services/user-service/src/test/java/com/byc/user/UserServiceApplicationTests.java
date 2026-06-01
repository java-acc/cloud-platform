package com.byc.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
    "spring.cloud.nacos.discovery.enabled=false", "spring.cloud.nacos.config.enabled=false",
    "spring.cloud.sentinel.enabled=false",
    "spring.autoconfigure.exclude=" + "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,"
        + "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,"
        + "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,"
        + "com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration"})
class UserServiceApplicationTests {

  @Test
  void contextLoads() {
    // smoke test placeholder
  }
}
