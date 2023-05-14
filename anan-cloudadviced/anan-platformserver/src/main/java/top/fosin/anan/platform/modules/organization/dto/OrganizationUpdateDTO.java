package top.fosin.anan.platform.modules.organization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.*;

/**
 * 系统机构表(anan_organization)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统机构表更新DTO", description = "系统机构表(anan_organization)更新DTO")
public class OrganizationUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 181245832332372271L;
    
    @NotNull(message = "父机构编码，取值于id，表示当前数据所属的父类机构" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "父机构编码，取值于id，表示当前数据所属的父类机构", required = true, example = "Long")
    private Long pid;

    @Positive(message = "顶级机构序号" + "{javax.validation.constraints.Positive.message}")
    @NotNull(message = "顶级机构序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "顶级机构编号：指用户注册的机构，通常是一个集团组的最高级别机构，新增和修改数据时必填!")
    private Long topId;

    @NotBlank(message = "机构编码" + "{javax.validation.constraints.NotBlank.message}")
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    @ApiModelProperty(value = "机构编码：自定义机构编码，下级机构必须以上级机构编码为前缀，新增和修改数据时必填!")
    private String code;

    @NotBlank(message = "机构名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "机构名称：新增和修改数据时必填!")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @PositiveOrZero(message = "深度" + "{javax.validation.constraints.Positive.message}")
    @NotNull(message = "深度" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "深度，新增和修改数据时必填!")
    private Integer level;

    @ApiModelProperty(value = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构全名不能包含特殊字符")
    @NotBlank(message = "机构全名" + "{javax.validation.constraints.NotBlank.message}")
    private String fullname;

    @ApiModelProperty(value = "机构地址")
    private String address;

    @ApiModelProperty(value = "机构电话")
    private String telphone;

    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}")
    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11，新增和修改数据时必填!")
    private Integer status;


}
