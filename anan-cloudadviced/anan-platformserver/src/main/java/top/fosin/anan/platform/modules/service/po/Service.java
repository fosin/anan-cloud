package top.fosin.anan.platform.modules.service.po;


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
 * 系统服务表(AnanService)实体类
 *
 * @author fosin
 * @date 2020-12-05 17:37:58
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_service")
@Schema(description = "服务的实体类")
public class Service extends IdCreateUpdatePO<Long> {
  private static final long serialVersionUID = -94381323292251990L;

  @Basic
  @Column(name = "code")
  @Schema(description = "服务标识")
  private String code;

  @Basic
  @Column(name = "name")
  @Schema(description = "服务名称")
  private String name;

  @Basic
  @Column(name = "status")
  @Schema(description = "状态码：0：禁用 1：启用")
  private Byte status;

}
