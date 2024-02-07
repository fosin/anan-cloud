package top.fosin.anan.platform.modules.version.po;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.po.IdCreateUpdatePO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * 系统版本角色表(AnanVersionRole)实体类
 *
 * @author fosin
 * @date 2019-01-28 12:50:42
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "anan_version_role")
@Schema(description = "版本角色的实体类")
public class VersionRole extends IdCreateUpdatePO<Long> {
  private static final long serialVersionUID = 49090001033551923L;

  @Basic
  @Schema(description = "版本序号")
  @Column(name = "version_id", nullable = false)
  private Long versionId;

  @Basic
  @Schema(description = "角色名称")
  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @Basic
  @Schema(description = "角色标识")
  @Column(name = "value", nullable = false, length = 64)
  private String value;

  @Basic
  @Schema(description = "角色说明")
  @Column(name = "tips", length = 255)
  private String tips;

  @Basic
  @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11")
  @Column(name = "status", nullable = false)
  private Byte status;

}
