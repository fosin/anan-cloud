package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统版本角色表(CdpVersionRole)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本角色表查询DTO", description = "表(cdp_version_role)的对应的查询DTO")
public class CdpVersionRoleRetrieveDto implements Serializable {
    private static final long serialVersionUID = -49705835224748973L;
    
    @ApiModelProperty(value = "角色ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "角色名称", example = "String")
    private String name;

    @ApiModelProperty(value = "角色标识", example = "String")
    private String value;

    @ApiModelProperty(value = "角色说明", example = "String")
    private String tips;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_dictionary.code=11", example = "Integer")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_user.id", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新时间", example = "Date")
    private Date updateTime;

    @ApiModelProperty(value = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_user.id", example = "Long")
    private Long updateBy;

}