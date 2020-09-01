package com.github.fosin.anan.cloudresource.dto.request;

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
 * 系统用户表(AnanUser)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 12:23:44
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户表更新DTO", description = "表(anan_user)的对应的更新DTO")
public class AnanUserUpdateDto implements Serializable {
    private static final long serialVersionUID = -38545495043403316L;

    @NotNull(message = "用户ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "用户ID, 主键", required = true)
    private Long id;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", required = true)
    private Long organizId;

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
    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15", required = true)
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱")
    @Email(message = "电子邮箱格式无效")
    private String email;

    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date expireTime;

}
