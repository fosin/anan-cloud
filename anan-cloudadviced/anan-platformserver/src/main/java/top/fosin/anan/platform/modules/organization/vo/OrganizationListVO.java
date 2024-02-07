package top.fosin.anan.platform.modules.organization.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.IdPid;
/**
 * 系统机构表(anan_organization)集合VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统机构表(anan_organization)集合VO")
public class OrganizationListVO extends IdPid<Long> {
    private static final long serialVersionUID = -32252634518375818L;
    @Schema(description = "顶级机构编码：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id")
    private Long topId;

    @Schema(description = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀")
    private String code;

    @Schema(description = "机构名称")
    private String name;

    @Schema(description = "深度")
    private Integer level;

    @Schema(description = "机构全名")
    private String fullname;

    @Schema(description = "机构地址")
    private String address;

    @Schema(description = "机构电话")
    private String telphone;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

}
