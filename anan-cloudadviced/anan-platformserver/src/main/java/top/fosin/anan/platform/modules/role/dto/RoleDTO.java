package top.fosin.anan.platform.modules.role.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 系统角色表(anan_role)DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色表(anan_role)DTO")
public class RoleDTO extends Id<Long> {
    private static final long serialVersionUID = -74709393664221028L;

    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @Schema(description = "角色名称", example = "String")
    private String name;

    @Schema(description = "角色标识", example = "String")
    private String value;

    @Schema(description = "角色说明", example = "String")
    private String tips;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Byte status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建时间", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改时间", example = "Date")
    private Date updateTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;

    @Schema(description = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是", example = "Integer")
    private Byte builtIn;

}
