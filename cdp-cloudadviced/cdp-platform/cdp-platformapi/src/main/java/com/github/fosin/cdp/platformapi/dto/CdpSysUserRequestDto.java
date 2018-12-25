package com.github.fosin.cdp.platformapi.dto;

import java.util.Date;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统用户表(CdpSysUser)实体类输入DTO
 *
 * @author fosin
 * @date 2018-12-25 09:36:34
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户表实体类请求DTO", description = "表(cdp_sys_user)的对应的实体类请求DTO")
public class CdpSysUserRequestDto implements Serializable {
    private static final long serialVersionUID = 589340945082981177L;

    @Data
    @ApiModel(value = "系统用户表创建DTO", description = "表(cdp_sys_user)的对应的创建DTO")
    public static class CreateDto implements Serializable {
        private static final long serialVersionUID = 971494157919105918L;

        @NotNull(message = "机构ID不能为空")
        @ApiModelProperty(value = "机构ID", required = true)
        private Long organizId;

        @NotBlank
        @ApiModelProperty(value = "用户工号", required = true)
        @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
        private String usercode;

        @NotBlank
        @ApiModelProperty(value = "用户姓名", required = true)
        @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
        private String username;


        @NotBlank
        @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", required = true)
        private String password;

        @Past(message = "生日必须是一个过去的日期")
        @NotNull
        @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
        @ApiModelProperty(value = "生日", required = true)
        private Date birthday;

        @NotNull
        @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_sys_dictionary.code=15", required = true)
        private Integer sex;

        @ApiModelProperty(value = "电子邮箱")
        @Email(message = "电子邮箱格式无效")
        private String email;

        @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "头像")
        private String avatar;

        @NotNull
        @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", required = true, example = "0")
        private Integer status;

        @NotNull
        @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
        @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统")
        private Date expireTime;
    }

    @Data
    @ApiModel(value = "系统用户表更新DTO", description = "表(cdp_sys_user)的对应的更新DTO")
    public static class UpdateDto implements Serializable {
        private static final long serialVersionUID = 918600461408684870L;

        @NotNull(message = "用户ID不能为空")
        @ApiModelProperty(value = "用户ID", required = true)
        private Long id;

        @NotNull(message = "机构ID不能为空")
        @ApiModelProperty(value = "机构ID", required = true)
        private Long organizId;

        @NotBlank
        @ApiModelProperty(value = "用户工号", required = true)
        @Pattern(regexp = "[A-Za-z][A-Za-z0-9]{1,30}", message = "用户工号只能大小写字母开头，数字、下杠(_)组合而成,长度不超过30位")
        private String usercode;

        @NotBlank
        @ApiModelProperty(value = "用户姓名", required = true)
        @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
        private String username;

        @NotBlank
        @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", required = true)
        private String password;

        @Past(message = "生日必须是一个过去的日期")
        @NotNull
        @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
        @ApiModelProperty(value = "生日", required = true)
        private Date birthday;

        @NotNull
        @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_sys_dictionary.code=15", required = true)
        private Integer sex;

        @ApiModelProperty(value = "电子邮箱")
        @Email(message = "电子邮箱格式无效")
        private String email;

        @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "头像")
        private String avatar;

        @NotNull
        @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", required = true, example = "0")
        private Integer status;

        @NotNull
        @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
        @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统")
        private Date expireTime;
    }
}