package top.fosin.anan.platform.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.jpa.entity.CreateUpdateEntity;

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
@ApiModel(value = "服务的实体类", description = "服务的实体类")
public class AnanServiceEntity extends CreateUpdateEntity<Long> {
  private static final long serialVersionUID = -94381323292251990L;

  @Basic
  @Column(name = "code")
  @ApiModelProperty(value = "服务标识")
  private String code;

  @Basic
  @Column(name = "name")
  @ApiModelProperty(value = "服务名称")
  private String name;

  @Basic
  @Column(name = "status")
  @ApiModelProperty(value = "状态码：0：禁用 1：启用")
  private Integer status;

}
