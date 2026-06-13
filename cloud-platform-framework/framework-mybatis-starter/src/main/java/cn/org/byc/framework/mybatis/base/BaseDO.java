package cn.org.byc.framework.mybatis.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * mybatis base model
 *
 * @author Ken
 */
public abstract class BaseDO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @TableField(fill = FieldFill.INSERT)
  private Long creator;
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updater;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  @TableLogic
  private Boolean deleted;
  @Version
  private Integer version;


  public Long getCreator() {
    return creator;
  }

  public void setCreator(Long creator) {
    this.creator = creator;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public Long getUpdater() {
    return updater;
  }

  public void setUpdater(Long updater) {
    this.updater = updater;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
