package top.fosin.anan.platform.dto.res;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

/**
 * 系统版本表(AnanVersion)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统版本表响应DTO", description = "系统版本的响应DTO")
public class AnanVersionRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 622911122470978997L;
    @ApiModelProperty(value = "版本名称", example = "String")
    private String name;

    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版", example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "版本价格", example = "Double")
    private Double price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动开始日期", example = "Date")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动结束日期", example = "Date")
    private Date endTime;

    @ApiModelProperty(value = "有效期：一般按天计算", example = "Integer")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期", example = "Integer")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", example = "Integer")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", example = "Integer")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数", example = "Integer")
    private Integer tryoutDays;

    @ApiModelProperty(value = "启用状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "版本描述", example = "String")
    private String description;

}
