package com.github.fosin.anan.pojo.dto.request;

import com.github.fosin.anan.util.DateTimeUtil;
import com.github.fosin.anan.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
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
public class AnanUserRegisterDto implements Serializable {

    @NotBlank(message = "用户工号" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "用户工号", required = true)
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @NotBlank(message = "用户姓名" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "用户姓名", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @NotBlank(message = "密码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", required = true)
    private String password;

    @Past(message = "生日必须是一个过去的日期")
    @ApiModelProperty(value = "生日", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date birthday;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.id=15", required = true)
    private Integer sex;

    @Email(message = "电子邮箱格式无效")
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11", required = true)
    private Integer status;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
