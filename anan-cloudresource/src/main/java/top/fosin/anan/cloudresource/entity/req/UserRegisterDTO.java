package top.fosin.anan.cloudresource.entity.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.core.util.RegexUtil;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author fosin
 * @date 2018.12.5
 */
@Data
@Schema(description = "创建用户")
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = 8067979204680924269L;
    @NotBlank(message = "用户工号" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "用户工号")
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @NotBlank(message = "用户姓名" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "用户姓名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @NotBlank(message = "密码" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "传入原始密码，后台会对原始密码进行加密后再存储")
    private String password;

    @Past(message = "生日必须是一个过去的日期")
    @Schema(description = "生日")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date birthday;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "使用状态：具体取值于字典表anan_dictionary.id=15")
    private Byte sex;

    @Email(message = "电子邮箱格式无效")
    @Schema(description = "电子邮箱")
    private String email;

    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @Schema(description = "手机号码")
    private String phone;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}")
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11")
    private Byte status;

    @Schema(description = "头像")
    private String avatar;
}
