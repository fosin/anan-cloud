package top.fosin.anan.cloudresource.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.*;
import java.util.Date;
        
/**
 * 系统用户表(anan_user)更新DTO
 *
 * @author fosin
 * @date 2023-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统用户表更新DTO", description = "系统用户表(anan_user)更新DTO")
public class UserUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 597848911831040730L;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @NotBlank(message = "用户工号" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "用户工号", example = "String")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @NotBlank(message = "用户姓名" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "用户姓名", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @ApiModelProperty(value = "姓氏", example = "String")
    private String familyName;

    @ApiModelProperty(value = "中间名", example = "String")
    private String middleName;

    @ApiModelProperty(value = "名字", example = "String")
    private String givenName;

    @ApiModelProperty(value = "昵称", example = "String")
    private String nickname;

    @ApiModelProperty(value = "希望被称呼的名字", example = "String")
    private String preferredUsername;

    @ApiModelProperty(value = "网站地址", example = "String")
    private String website;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生日", example = "Date")
    @Past(message = "生日" + "{javax.validation.constraints.Past.message}",
            groups = {Create.class, Update.class})
    @NotNull(message = "生日" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Date birthday;

    @NotNull(message = "使用状态：具体取值于字典表anan_dictionary.code=15" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15", example = "Integer")
    @Positive(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱", example = "String")
    @Email(message = "电子邮箱{javax.validation.constraints.Email.message}", groups = {Create.class, Update.class})
    private String email;

    @ApiModelProperty(value = "手机号码", example = "String")
    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码{javax.validation.constraints.Pattern.message}",
            groups = {Create.class, Update.class})
    private String phone;

    @NotNull(message = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "头像", example = "String")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", example = "Date")
    @NotNull(message = "过期时间" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Date expireTime;

}
