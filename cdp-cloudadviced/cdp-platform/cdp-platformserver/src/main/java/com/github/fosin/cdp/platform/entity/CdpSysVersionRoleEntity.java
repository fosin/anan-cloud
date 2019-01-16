package com.github.fosin.cdp.platform.entity;

import com.github.fosin.cdp.jpa.entity.AbstractCreateUpdateJpaEntity;
import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统版本角色表(CdpSysVersionRole)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_version_role")
@ApiModel(value = "系统版本角色表实体类", description = "表(cdp_sys_version_role)的对应的实体类")
public class CdpSysVersionRoleEntity extends AbstractCreateUpdateJpaEntity implements Serializable {
    private static final long serialVersionUID = -75498558716473872L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,角色ID")
    private Long id;

    @Basic
    @NotNull
    @Column(name = "version_id")
    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @Basic
    @NotBlank
    @Column(name = "name")
    @ApiModelProperty(value = "角色名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @Basic
    @NotBlank
    @Column(name = "value")
    @ApiModelProperty(value = "角色标识")
    @Pattern(regexp = "[\\w]{1,40}", message = "角色标识只能大小写字母、数字、下杠(_)组合而成,长度不超过40位")
    private String value;

    @Basic
    @Column(name = "tips")
    @ApiModelProperty(value = "角色说明")
    private String tips;

    @Basic
    @NotNull
    @Column(name = "status")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;
}