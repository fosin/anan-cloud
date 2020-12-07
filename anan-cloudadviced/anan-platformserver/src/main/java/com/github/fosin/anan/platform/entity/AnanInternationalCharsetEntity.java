package com.github.fosin.anan.platform.entity;

import com.github.fosin.anan.jpa.entity.AbstractCreateUpdateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 国际化语言字符集(AnanInternationalCharset)实体类
 *
 * @author fosin
 * @date 2020-12-05 17:40:53
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_international_charset")
@ApiModel(value = "表(anan_international_charset)的对应的实体类", description = "表(anan_international_charset)的对应的实体类")
public class AnanInternationalCharsetEntity extends AbstractCreateUpdateJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = 989647017404761569L;

    @Basic
    @Column(name = "international_id")
    @ApiModelProperty(value = "国际化语言ID")
    private Integer internationalId;

    @Basic
    @Column(name = "key")
    @ApiModelProperty(value = "代码名称")
    private String key;

    @Basic
    @Column(name = "value")
    @ApiModelProperty(value = "对应语言显示名")
    private String value;

    @Basic
    @Column(name = "service_id")
    @ApiModelProperty(value = "服务ID")
    private Integer serviceId;

    @Basic
    @Column(name = "status")
    @ApiModelProperty(value = "状态：0=启用，1=禁用")
    private Integer status;

}
