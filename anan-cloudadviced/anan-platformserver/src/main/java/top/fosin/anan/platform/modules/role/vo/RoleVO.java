package top.fosin.anan.platform.modules.role.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

        /**
 * 系统角色表(anan_role)单体VO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色表(anan_role)单体VO")
public class RoleVO extends Id<Long> {
    private static final long serialVersionUID = -41476243176681768L;
    @Schema(description = "机构ID")
    private Long organizId;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色标识")
    private String value;

    @Schema(description = "角色说明")
    private String tips;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @Schema(description = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是")
    private Byte builtIn;

}
