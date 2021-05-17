package top.fosin.anan.platform.dto.request;

import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限查询DTO", description = "表(anan_user_permission)的对应的查询DTO")
public class AnanUserPermissionRetrieveDto extends QuerySortRuleDto<QueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = 989390435758584592L;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

}
