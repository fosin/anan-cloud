package com.github.fosin.cdp.platform.dto.request;

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
 * 系统版本表(CdpSysVersion)创建DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本表创建DTO", description = "表(cdp_sys_version)的对应的创建DTO")
public class CdpSysVersionCreateDto implements Serializable {
    private static final long serialVersionUID = 710906573524766524L;
    
    @NotBlank
    @ApiModelProperty(value = "版本名称", example = "String", required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", example = "Integer", required = true)
    private Integer type;

    @NotNull
    @ApiModelProperty(value = "版本价格", example = "Double", required = true)
    private Double price;

    @ApiModelProperty(value = "开始日期", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date beginTime;

    @ApiModelProperty(value = "结束日期", example = "Date", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date endTime;

    @NotNull
    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer", required = true)
    private Integer validity;

    @NotNull
    @ApiModelProperty(value = "到期后保护期", example = "Integer", required = true)
    private Integer protectDays;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer", required = true)
    private Integer maxOrganizs;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer", required = true)
    private Integer maxUsers;

    @NotNull
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer", required = true)
    private Integer tryout;

    @NotNull
    @ApiModelProperty(value = "试用天数", example = "Integer", required = true)
    private Integer tryoutDays;

    @NotNull
    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", example = "Integer", required = true)
    private Integer status;

    @ApiModelProperty(value = "版本描述", example = "String")
    private String description;

}