package com.github.fosin.cdp.platformapi.dto.request;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户角色表(CdpUserRole)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户角色表查询DTO", description = "表(cdp_user_role)的对应的查询DTO")
public class CdpUserRoleRetrieveDto implements Serializable {
    private static final long serialVersionUID = 818450290607468187L;

    @ApiModelProperty(value = "用户角色ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户ID", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long roleId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_user.id", example = "Long")
    private Long createBy;

}
