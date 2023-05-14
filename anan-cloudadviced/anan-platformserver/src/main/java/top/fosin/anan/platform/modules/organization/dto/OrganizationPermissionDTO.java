package top.fosin.anan.platform.modules.organization.dto;


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
 * 系统机构权限表(anan_organization_permission)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统机构权限表DTO", description = "系统机构权限表(anan_organization_permission)DTO")
public class OrganizationPermissionDTO extends Id<Long> {
    private static final long serialVersionUID = 199375059620997767L;
    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "权限ID", required = true, example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

}
