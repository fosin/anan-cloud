package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统用户表(CdpSysUser)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:19
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户表查询DTO", description = "表(cdp_sys_user)的对应的查询DTO")
public class CdpSysUserRetrieveDto implements Serializable {
    private static final long serialVersionUID = 369161271342763695L;
    
    @NotNull
    @ApiModelProperty(value = "用户ID", example = "Long")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @NotBlank
    @ApiModelProperty(value = "用户工号", example = "String")
    private String usercode;

    @NotBlank
    @ApiModelProperty(value = "用户姓名", example = "String")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", example = "String")
    private String password;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生日", example = "Date")
    private Date birthday;

    @NotNull
    @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_sys_dictionary.code=15", example = "Integer")
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱", example = "String")
    private String email;

    @ApiModelProperty(value = "手机号码", example = "String")
    private String phone;

    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "头像", example = "String")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", example = "Date")
    private Date updateTime;

    @NotNull
    @ApiModelProperty(value = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", example = "Date")
    private Date expireTime;

}