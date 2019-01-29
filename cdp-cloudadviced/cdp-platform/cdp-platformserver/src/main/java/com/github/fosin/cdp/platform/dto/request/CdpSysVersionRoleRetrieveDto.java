package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统版本角色表(CdpSysVersionRole)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本角色表查询DTO", description = "表(cdp_sys_version_role)的对应的查询DTO")
public class CdpSysVersionRoleRetrieveDto implements Serializable {
    private static final long serialVersionUID = 489654690197341765L;
    
    @NotNull
    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long versionId;

    @NotBlank
    @ApiModelProperty(value = "角色名称", example = "String")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "角色标识", example = "String")
    private String value;

    @ApiModelProperty(value = "角色说明", example = "String")
    private String tips;

    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.id=11", example = "Integer")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新时间", example = "Date")
    private Date updateTime;

    @NotNull
    @ApiModelProperty(value = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long updateBy;

}