package cn.org.byc.framework.common.config;

import cn.org.byc.framework.common.util.SpringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Ken
 */
@AutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringUtilAutoConfiguration {

  @Bean
  public SpringUtils springUtils() {
    return new SpringUtils();
  }

}
