package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统用户表(CdpUser)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 12:23:44
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户表更新DTO", description = "表(cdp_user)的对应的更新DTO")
public class CdpUserUpdateDto implements Serializable {
    private static final long serialVersionUID = -55634872988978832L;

    @NotNull
    @ApiModelProperty(value = "用户ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotBlank
    @ApiModelProperty(value = "用户工号", example = "String", required = true)
    @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @NotBlank
    @ApiModelProperty(value = "用户姓名", example = "String", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", example = "String", required = true)
    private String password;

    @Past(message = "生日必须是一个过去的日期")
    @ApiModelProperty(value = "生日", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date birthday;

    @NotNull
    @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_dictionary.id=15", example = "Integer", required = true)
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱", example = "String")
    @Email(message = "电子邮箱格式无效")
    private String email;

    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码", example = "String")
    private String phone;

    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_dictionary.id=11", example = "Integer", required = true)
    private Integer status;

    @ApiModelProperty(value = "头像", example = "String")
    private String avatar;

    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date expireTime;

}
