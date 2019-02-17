package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.jpa.entity.AbstractCreateUpdateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
/**
 * 系统机构表(CdpOrganization)实体类
 *
 * @author fosin
 * @date 2019-01-27 18:42:49
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "cdp_organization")
@ApiModel(value = "系统机构表实体类", description = "表(cdp_organization)的对应的实体类")
public class CdpOrganizationEntity extends AbstractCreateUpdateJpaEntity<Long, Long> implements Serializable {
    private static final long serialVersionUID = -27331190994806707L;

    @Basic
    @ApiModelProperty(value = "父机构编码，取值于id，表示当前数据所属的父类机构", required = true)
    @Column(name = "p_id", nullable = false)
    private Long pId;

    @Basic
    @ApiModelProperty(value = "顶级机构编码：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", required = true)
    @Column(name = "top_id", nullable = false)
    private Long topId;

    @Basic
    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", required = true)
    @Column(name = "code", nullable = false, length = 64)
    private String code;

    @Basic
    @ApiModelProperty(value = "机构名称", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "深度", required = true)
    @Column(name = "level", nullable = false)
    private Integer level;

    @Basic
    @ApiModelProperty(value = "机构全名")
    @Column(name = "fullname", length = 128)
    private String fullname;

    @Basic
    @ApiModelProperty(value = "机构地址")
    @Column(name = "address", length = 128)
    private String address;

    @Basic
    @ApiModelProperty(value = "机构电话")
    @Column(name = "telphone", length = 24)
    private String telphone;

    @Basic
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_dictionary.id=11", required = true)
    @Column(name = "status", nullable = false)
    private Integer status;

}
