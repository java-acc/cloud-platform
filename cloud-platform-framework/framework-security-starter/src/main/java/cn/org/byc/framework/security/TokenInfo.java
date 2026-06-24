package cn.org.byc.framework.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ken
 */
@Getter
@Setter
public class TokenInfo {

  /**
   * token
   */
  private String token;
  /**
   * 过期时间(毫秒)
   */
  private Long expireTime;
}
