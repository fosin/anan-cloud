package top.fosin.anan.platform.modules.organization.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.*;

/**
 * 系统机构表(anan_organization)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统机构表(anan_organization)创建DTO")
public class OrganizationCreateDTO {
    private static final long serialVersionUID = 409087398765392282L;

    @NotNull(message = "父机构编码，取值于id，表示当前数据所属的父类机构" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "父机构编码，取值于id，表示当前数据所属的父类机构", example = "Long")
    private Long pid;

    @Positive(message = "顶级机构序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @NotNull(message = "顶级机构序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "顶级机构编号：指用户注册的机构，通常是一个集团组的最高级别机构，新增和修改数据时必填!")
    private Long topId;

    @NotBlank(message = "机构编码" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位", groups = Create.class)
    @Schema(description = "机构编码：自定义机构编码，下级机构必须以上级机构编码为前缀，新增和修改数据时必填!")
    private String code;

    @NotBlank(message = "机构名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "机构名称：新增和修改数据时必填!")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Create.class)
    private String name;

    @Schema(description = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构全名不能包含特殊字符", groups = Create.class)
    @NotBlank(message = "机构全名" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    private String fullname;

    @Schema(description = "机构地址")
    private String address;

    @Schema(description = "机构电话")
    private String telphone;

    @Min(value = 0, message = "使用状态" + "{jakarta.validation.constraints.Min.message}", groups = Create.class)
    @Max(value = 1, message = "使用状态" + "{jakarta.validation.constraints.Max.message}", groups = Create.class)
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11，新增和修改数据时必填!")
    private Byte status;

}
