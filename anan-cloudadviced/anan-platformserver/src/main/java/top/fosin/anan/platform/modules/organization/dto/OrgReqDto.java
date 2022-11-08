package top.fosin.anan.platform.modules.organization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.req.IdLogiSortPidQuery;
import top.fosin.anan.data.module.LogicalQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Delete;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.*;

/**
 * 系统机构表(AnanOrganization)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "机构表创建DTO", description = "机构的创建DTO")
public class OrgReqDto extends IdLogiSortPidQuery<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 389815217019211695L;

    @Positive(message = "顶级机构序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class, Delete.class})
    @NotNull(message = "顶级机构序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "顶级机构编号：指用户注册的机构，通常是一个集团组的最高级别机构，新增和修改数据时必填!")
    private Long topId;

    @NotBlank(message = "机构编码" + "{javax.validation.constraints.NotBlank.message}")
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位",
            groups = {Create.class, Update.class, Delete.class})
    @ApiModelProperty(value = "机构编码：自定义机构编码，下级机构必须以上级机构编码为前缀，新增和修改数据时必填!")
    private String code;

    @NotBlank(message = "机构名称" + "{javax.validation.constraints.NotBlank.message}",
    groups = {Create.class, Update.class})
    @ApiModelProperty(value = "机构名称：新增和修改数据时必填!")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符",
            groups = {Create.class, Update.class, Delete.class})
    private String name;

    @PositiveOrZero(message = "深度" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @NotNull(message = "深度" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "深度，新增和修改数据时必填!")
    private Integer level;

    @ApiModelProperty(value = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构全名不能包含特殊字符",
            groups = {Create.class, Update.class})
    @NotBlank(message = "机构全名" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    private String fullname;

    @ApiModelProperty(value = "机构地址")
    private String address;

    @ApiModelProperty(value = "机构电话")
    private String telphone;

    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11，新增和修改数据时必填!")
    private Integer status;

}
