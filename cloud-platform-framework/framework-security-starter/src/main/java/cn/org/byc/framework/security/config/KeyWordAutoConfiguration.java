/*
 * Copyright 2026 Ken
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.byc.framework.security.config;

import cn.org.byc.framework.security.config.properties.TokenProperties;
import cn.org.byc.framework.security.constant.SecurityKeyWord;
import cn.org.byc.framework.security.constant.SecurityKeyWordImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Ken
 */
@AutoConfiguration
@EnableConfigurationProperties(TokenProperties.class)
public class KeyWordAutoConfiguration {

  @Bean(name = "securityKeyWord")
  @ConditionalOnMissingBean(SecurityKeyWord.class)
  public SecurityKeyWord securityKeyWord() {
    return new SecurityKeyWordImpl();
  }
}
