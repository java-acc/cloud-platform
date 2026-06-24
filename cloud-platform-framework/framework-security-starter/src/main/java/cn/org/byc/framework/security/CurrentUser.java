package cn.org.byc.framework.security;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ken
 */
@Getter
@Setter
public class CurrentUser implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String clientId;
  private String tenantId;
  /**
   * 账号
   */
  private String userCode;
  private Long userId;
  private String userName;

  private String deptId;

  private String roleId;
  private String roleName;
}
