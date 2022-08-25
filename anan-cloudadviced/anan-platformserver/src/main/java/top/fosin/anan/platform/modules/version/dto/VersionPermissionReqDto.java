package top.fosin.anan.platform.modules.version.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortQuery;
import top.fosin.anan.data.module.LogicalQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 系统版本权限表(AnanVersionPermission)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "版本权限表请求DTO", description = "版本权限的请求DTO")
public class VersionPermissionReqDto extends IdLogiSortQuery<LogicalQueryRule, SortRule, Long>
        implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 425131909775170449L;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "版本序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "权限序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限序号", required = true)
    private Long permissionId;

    @Override
    public Long getFkValue() {
        return versionId;
    }

    @Override
    public void setFkValue(Long foreingKey) {
        this.versionId = foreingKey;
    }

    @Override
    public String getFkName() {
        return "versionId";
    }
}
