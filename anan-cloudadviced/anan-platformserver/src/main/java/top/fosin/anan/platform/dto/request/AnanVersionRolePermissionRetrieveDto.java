package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.QueryRule;
import top.fosin.anan.model.module.SortRule;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统版本角色权限表查询DTO", description = "表(anan_version_role_permission)的对应的查询DTO")
public class AnanVersionRolePermissionRetrieveDto extends QuerySortRuleDto<QueryRule,SortRule,Long> implements Serializable {
    private static final long serialVersionUID = -42309427704059941L;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

}
