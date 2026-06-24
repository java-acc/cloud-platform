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

package cn.org.byc.framework.security.config.properties;

import cn.org.byc.framework.security.constant.SecurityKeyWord;
import io.jsonwebtoken.JwtException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Ken
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "smart.security.token")
public class TokenProperties {

  private String signKey;

  public String getSignKey() {
    if (this.signKey.length() < SecurityKeyWord.SIGN_KEY_LENGTH) {
			throw new JwtException("The smart.security.token.sign-key property is required and must be at least 32 characters long.");
		}
		return this.signKey;
  }
}
