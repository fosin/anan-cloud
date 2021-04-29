package top.fosin.anan.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;

/**
 * 系统版本权限表(AnanVersionPermission)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本权限表查询DTO", description = "表(anan_version_permission)的对应的查询DTO")
public class AnanVersionPermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = 425131909775170449L;

    @ApiModelProperty(value = "版本权限ID, 主键")
    private Long id;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

}
