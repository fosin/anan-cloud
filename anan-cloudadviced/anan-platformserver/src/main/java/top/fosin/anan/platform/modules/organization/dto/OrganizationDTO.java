package top.fosin.anan.platform.modules.organization.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.IdPid;

import java.util.Date;
/**
 * 系统机构表(anan_organization)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统机构表(anan_organization)DTO")
public class OrganizationDTO extends IdPid<Long> {
    private static final long serialVersionUID = 515822659314087790L;
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

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改日期", example = "Date")
    private Date updateTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;

}
