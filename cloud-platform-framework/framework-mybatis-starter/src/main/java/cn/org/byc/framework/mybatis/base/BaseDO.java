package cn.org.byc.framework.mybatis.base;

import cn.org.byc.framework.ddd.Entity;
import cn.org.byc.framework.mybatis.enums.DataStatusEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * mybatis base model
 *
 * @author Ken
 */
@Getter
@Setter
public abstract class BaseDO extends Entity<Long> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  @TableField(fill = FieldFill.INSERT)
  private Long createdId;
  @TableField(fill = FieldFill.INSERT)
  private String createdName;
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createdDate;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updatedId;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updatedName;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updatedDate;
  @TableLogic
  @TableField(fill = FieldFill.INSERT)
  private Integer deleted;
  @Version
  @TableField(fill = FieldFill.INSERT)
  private Integer version;
  @TableField(fill = FieldFill.INSERT)
  private DataStatusEnum status;

}
