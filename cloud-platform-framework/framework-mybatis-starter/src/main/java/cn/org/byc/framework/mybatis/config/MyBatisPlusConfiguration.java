package cn.org.byc.framework.mybatis.config;

import cn.org.byc.framework.mybatis.base.plugin.CustomPaginationInterceptor;
import cn.org.byc.framework.mybatis.base.plugin.SqlLogInterceptor;
import cn.org.byc.framework.mybatis.config.properties.LogProperties;
import cn.org.byc.framework.mybatis.config.properties.PaginationProperties;
import cn.org.byc.framework.mybatis.config.properties.TenantProperties;
import cn.org.byc.framework.mybatis.handler.FieldsFillingHandler;
import cn.org.byc.framework.mybatis.handler.TenantLineHandlerImpl;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties.CoreConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.parser.JsqlParserGlobal;
import com.baomidou.mybatisplus.extension.parser.cache.JdkSerialCaffeineJsqlParseCache;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Ken
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties({TenantProperties.class, LogProperties.class, PaginationProperties.class})
public class MyBatisPlusConfiguration {

  static {
    // 动态 SQL 智能优化支持本地缓存加速解析，更完善的租户复杂 XML 动态 SQL 支持，静态注入缓存
    JsqlParserGlobal.setJsqlParseCache(new JdkSerialCaffeineJsqlParseCache(
        (cache) -> cache.maximumSize(1024)
            .expireAfterWrite(5, TimeUnit.SECONDS))
    );
  }

  @Bean
  @ConditionalOnMissingBean(MetaObjectHandler.class)
  public MetaObjectHandler metaObjectHandler() {
    return new FieldsFillingHandler();
  }

  /**
   * 租户拦截器
   */
  @Bean
  @ConditionalOnMissingBean(TenantLineInnerInterceptor.class)
  public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperties tenantProperties) {
    return new TenantLineInnerInterceptor(new TenantLineHandlerImpl(tenantProperties));
  }

  @Bean
  @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
  public MybatisPlusInterceptor mybatisPlusInterceptor(TenantLineInnerInterceptor tenantLineInnerInterceptor, PaginationProperties paginationProperties) {

    CustomPaginationInterceptor paginationInterceptor = new CustomPaginationInterceptor();
    paginationInterceptor.setMaxLimit(paginationProperties.getMaxLimit());
    paginationInterceptor.setOverflow(paginationProperties.getOverflow());
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // todo: 陷入矛盾，方案待定，是自己实现拦截器控制查询语句的权限还是使用DataPermissionInterceptor？因为可能出现有多种需要修改查询语句拦截器
    // interceptor.addInnerInterceptor(new DataPermissionInterceptor());
    interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor(Boolean.TRUE));
    interceptor.addInnerInterceptor(paginationInterceptor);
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
    return interceptor;
  }

  /**
   * sql 日志
   *
   * @return SqlLogInterceptor
   */
  @Bean
  public SqlLogInterceptor sqlLogInterceptor(LogProperties properties) {
    return new SqlLogInterceptor(properties);
  }

  /**
   * 关闭 mybatis 默认日志
   */
  @Bean
  public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
    return properties -> {
      CoreConfiguration configuration = properties.getConfiguration();
      if (configuration != null) {
        Class<? extends Log> logImpl = configuration.getLogImpl();
        if (logImpl == null) {
          configuration.setLogImpl(NoLoggingImpl.class);
        }
      }
    };
  }
}
