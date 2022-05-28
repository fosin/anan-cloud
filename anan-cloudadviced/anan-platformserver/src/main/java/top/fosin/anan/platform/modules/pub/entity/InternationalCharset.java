package top.fosin.anan.platform.modules.pub.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import top.fosin.anan.jpa.entity.IdCreateUpdateEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 国际化语言字符集实体类
 *
 * @author fosin
 * @date 2020-12-09 10:34:33
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_international_charset")
@ApiModel(value = "国际化语言字符集实体类", description = "国际化语言字符集实体类")
public class InternationalCharset extends IdCreateUpdateEntity<Long> {
    private static final long serialVersionUID = -47422702414130736L;

    @Basic
    @Column(name = "international_id")
    @ApiModelProperty(value = "国际化语言序号")
    private Long internationalId;

    @Basic
    @Column(name = "service_id")
    @ApiModelProperty(value = "服务序号")
    private Long serviceId;

    @Basic
    @Column(name = "charset")
    @ApiModelProperty(value = "自定义字符集")
    private String charset;

    @Basic
    @Column(name = "status")
    @ApiModelProperty(value = "状态：0=启用，1=禁用")
    private Integer status;


}
