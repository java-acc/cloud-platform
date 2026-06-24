package cn.org.byc.framework.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ken
 */
@Getter
@Setter
public class AuthInfo {
  private String tokenType;
  private String accessToken;
  private String refreshToken;

  private Long userId;
  private String userName;
  private String userCode;
  private String avatar;

  private String oauthId;

  private String authority;

  /**
   * 过期时间
   */
  private Long expires;
}
