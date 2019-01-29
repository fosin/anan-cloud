package com.github.fosin.cdp.platformapi.dto;

import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.5
 */
@Data
@ApiModel(value = "创建用户")
public class UserRegisterDto implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "用户工号", notes = "用户工号")
    @Pattern(regexp = "[\\w]{1,30}", message = "用户工号只能大小写字母、数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @NotBlank
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank
    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    @Past(message = "生日必须是一个过去的日期")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
//    @Pattern(regexp =RegexUtil.BIRTHDAY_DATETIME, message = "生日格式："+DateTimeUtil.DATETIME_PATTERN+",且在1900-01-01到2099-12-31之间")
    @ApiModelProperty(value = "生日", notes = "生日")
    private Date birthday;

    @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_sys_dictionary.id=15", notes = "使用状态：具体取值于字典表cdp_sys_dictionary.id=15")
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱", notes = "电子邮箱")
    @Email
    private String email;

    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码", notes = "手机号码")
    private String phone;

    @ApiModelProperty(value = "头像", notes = "头像")
    private String avatar;

}
