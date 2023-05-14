package top.fosin.anan.platform.modules.role.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 系统角色权限表(anan_role_permission)DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统角色权限表DTO", description = "系统角色权限表(anan_role_permission)DTO")
public class RolePermissionDTO extends Id<Long> {
    private static final long serialVersionUID = -96931662187329342L;
    @ApiModelProperty(value = "角色ID", required = true, example = "Long")
    private Long roleId;

    @ApiModelProperty(value = "权限ID", required = true, example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

}
