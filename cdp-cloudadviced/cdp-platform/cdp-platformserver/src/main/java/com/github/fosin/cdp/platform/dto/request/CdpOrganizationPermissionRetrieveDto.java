package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统机构权限表(CdpOrganizationPermission)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构权限表查询DTO", description = "表(cdp_organization_permission)的对应的查询DTO")
public class CdpOrganizationPermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = 258891966081450196L;
    
    @ApiModelProperty(value = "机构权限ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_user.id", example = "Long")
    private Long createBy;

}