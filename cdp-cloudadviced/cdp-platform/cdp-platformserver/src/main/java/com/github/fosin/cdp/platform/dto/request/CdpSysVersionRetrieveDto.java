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
 * 系统版本表(CdpSysVersion)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本表查询DTO", description = "表(cdp_sys_version)的对应的查询DTO")
public class CdpSysVersionRetrieveDto implements Serializable {
    private static final long serialVersionUID = 689652050124486312L;
    
    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long")
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "版本名称", example = "String")
    private String name;

    @NotNull
    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", example = "Integer")
    private Integer type;

    @NotNull
    @ApiModelProperty(value = "版本价格", example = "Double")
    private Double price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "开始日期", example = "Date")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "结束日期", example = "Date")
    private Date endTime;

    @NotNull
    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @NotNull
    @ApiModelProperty(value = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @NotNull
    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @NotNull
    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @NotNull
    @ApiModelProperty(value = "试用天数", example = "Integer")
    private Integer tryoutDays;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "版本描述", example = "String")
    private String description;

}