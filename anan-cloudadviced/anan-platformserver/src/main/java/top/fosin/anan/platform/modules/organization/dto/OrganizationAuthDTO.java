package top.fosin.anan.platform.modules.organization.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 系统机构授权表(anan_organization_auth)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统机构授权表DTO", description = "系统机构授权表(anan_organization_auth)DTO")
public class OrganizationAuthDTO extends Id<Long> {
    private static final long serialVersionUID = 439900534313942300L;

    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "版本ID", required = true, example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "订单ID", required = true, example = "Long")
    private Long orderId;

    @ApiModelProperty(value = "授权码", required = true, example = "String")
    private String authorizationCode;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

    @ApiModelProperty(value = "有效期：一般按天计算", required = true, example = "Integer")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期", required = true, example = "Integer")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true, example = "Integer")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数", required = true, example = "Integer")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用", required = true, example = "Integer")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数", required = true, example = "Integer")
    private Integer tryoutDays;

}
