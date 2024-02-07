package top.fosin.anan.platform.modules.version.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统版本角色表(anan_version_role)集合VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统版本角色表(anan_version_role)集合VO")
public class VersionRoleListVO extends Id<Long> {
    private static final long serialVersionUID = 523904514612772426L;
    @Schema(description = "版本ID")
    private Long versionId;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色标识")
    private String value;

    @Schema(description = "角色说明")
    private String tips;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

}
