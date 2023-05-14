package top.fosin.anan.platform.modules.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.cloudresource.grpc.service.UserGrpcServiceImpl;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.NotNull;

/**
 * 系统用户角色表(AnanUserRole)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户角色表响应DTO", description = "用户角色的响应DTO")
public class RoleUserVO extends Id<Long> {
    private static final long serialVersionUID = -30073122110919311L;

    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    private Long organizId;

    @ApiModelProperty(value = "用户序号", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "用户姓名", example = "String")
    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    private String username;

    @ApiModelProperty(value = "角色序号", example = "Long")
    private Long roleId;

}
