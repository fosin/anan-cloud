package top.fosin.anan.cloudresource.entity.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 系统用户表(anan_user)创建DTO
 *
 * @author fosin
 * @date 2023-05-02
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统用户表(anan_user)创建DTO")
public class UserCreateDTO {
    private static final long serialVersionUID = -48916474866773943L;

    @NotNull(message = "机构ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @NotBlank(message = "用户工号" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "用户工号", example = "String")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位", groups = Create.class)
    private String usercode;

    @NotBlank(message = "用户姓名" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "用户姓名", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符", groups = Create.class)
    private String username;

    @Schema(description = "姓氏", example = "String")
    private String familyName;

    @Schema(description = "中间名", example = "String")
    private String middleName;

    @Schema(description = "名字", example = "String")
    private String givenName;

    @Schema(description = "昵称", example = "String")
    private String nickname;

    @Schema(description = "希望被称呼的名字", example = "String")
    private String preferredUsername;

    @Schema(description = "网站地址", example = "String")
    private String website;

    @NotBlank(message = "传入原始密码，后台会对原始密码进行加密后再存储" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "传入原始密码，后台会对原始密码进行加密后再存储", example = "String")
    private String password;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "生日", example = "Date")
    @Past(message = "生日" + "{jakarta.validation.constraints.Past.message}",
            groups = {Create.class, Update.class})
    @NotNull(message = "生日" + "{jakarta.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Date birthday;

    @NotNull(message = "使用状态：具体取值于字典表anan_dictionary.code=15" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "使用状态：具体取值于字典表anan_dictionary.code=15", example = "Integer")
    @Positive(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    private Byte sex;

    @Schema(description = "电子邮箱", example = "String")
    @Email(message = "电子邮箱{jakarta.validation.constraints.Email.message}", groups = {Create.class, Update.class})
    private String email;

    @Schema(description = "手机号码", example = "String")
    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码{jakarta.validation.constraints.Pattern.message}",
            groups = {Create.class, Update.class})
    private String phone;

    @NotNull(message = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Byte status;

    @Schema(description = "头像", example = "String")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "过期时间，账户过期后用户被锁定切不能登录系统", example = "Date")
    @NotNull(message = "过期时间" + "{jakarta.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    private Date expireTime;

}
