package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 系统用户表(AnanUser)请求DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:19
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用户表请求DTO", description = "用户的请求DTO")
public class AnanUserReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -24340282458700184L;

    @NotNull(message = "机构序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "机构ID,创建和更新时必填")
    private Long organizId;

    @NotBlank(message = "用户工号" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "用户工号,创建和更新时必填")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @NotBlank(message = "用户姓名" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "用户姓名,创建和更新时必填")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @Past(message = "生日必须是一个过去的时间",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "生日,创建和更新时必填")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date birthday;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "使用状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15,创建和更新时必填")
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱")
    @Email(message = "电子邮箱格式无效")
    private String email;

    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 0, message = "使用状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11,创建和更新时必填")
    private Integer status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统,创建和更新时必填")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @NotNull(message = "过期时间" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Date expireTime;

    @ApiModelProperty(value = "创建人", example = "1")
    private Long createBy;

    @ApiModelProperty(value = "修改人", example = "1")
    private Long updateBy;

}
