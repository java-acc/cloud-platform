package cn.org.byc.framework.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

/**
 * @author Ken
 */
@Slf4j
public class SpringUtils implements ApplicationContextAware {

  private static ApplicationContext context;
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringUtils.context = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    if (context == null){
      log.warn("context is null");
    }
    return context;
  }

  public static void publishEvent(ApplicationEvent event) {
    Assert.notNull(context, "applicationContext can't be null");
    context.publishEvent(event);
  }

  public static <T> T getBean(String name) {
    Assert.notNull(context, "applicationContext can't be null");
    return (T) context.getBean(name);
  }

  public static <T> T getBean(Class<T> clazz) {
    Assert.notNull(context, "applicationContext can't be null");
    return context.getBean(clazz);
  }

  public static <T> T getBean(String name, Class<T> clazz) {
    Assert.notNull(context, "applicationContext can't be null");
    return context.getBean(name, clazz);
  }
}
