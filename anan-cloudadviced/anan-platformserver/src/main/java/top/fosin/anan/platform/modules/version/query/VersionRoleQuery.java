package top.fosin.anan.platform.modules.version.query;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

        /**
 * 系统版本角色表(anan_version_role)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统版本角色表(anan_version_role)通用查询DTO")
public class VersionRoleQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = -62846194641754908L;
    
    @Schema(description = "版本ID", example = "Long")
    private Long versionId;

    @Schema(description = "角色名称", example = "String")
    private String name;

    @Schema(description = "角色标识", example = "String")
    private String value;

    @Schema(description = "角色说明", example = "String")
    private String tips;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Byte status;

}
