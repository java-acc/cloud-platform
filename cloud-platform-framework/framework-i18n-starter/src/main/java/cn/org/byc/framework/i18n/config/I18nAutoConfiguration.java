package cn.org.byc.framework.i18n.config;

import cn.org.byc.framework.i18n.config.properties.I18nProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Ken
 */
@AutoConfiguration
@EnableConfigurationProperties(I18nProperties.class)
@ConditionalOnProperty(prefix = "smart.i18n", name = "enabled", havingValue = "true", matchIfMissing = true)
public class I18nAutoConfiguration {

}
