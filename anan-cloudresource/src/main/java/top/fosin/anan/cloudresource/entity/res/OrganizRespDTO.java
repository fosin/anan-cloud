package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.IdPid;

/**
 * 系统机构表(AnanOrganization)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Schema(description = "机构的响应DTO")
public class OrganizRespDTO extends IdPid<Long> {
    private static final long serialVersionUID = 386478582280754215L;
    @Schema(description = "顶级机构编码：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", example = "Long")
    private Long topId;

    @Schema(description = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", example = "String")
    private String code;

    @Schema(description = "机构名称", example = "String")
    private String name;

    @Schema(description = "深度", example = "Integer")
    private Integer level;

    @Schema(description = "机构全名", example = "String")
    private String fullname;

    @Schema(description = "机构地址", example = "String")
    private String address;

    @Schema(description = "机构电话", example = "String")
    private String telphone;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Byte status;

}
