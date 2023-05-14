package top.fosin.anan.platform.modules.organization.vo;

                                                                                                                                                                                                                            
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统机构授权表(anan_organization_auth)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统机构授权表单体VO", description = "系统机构授权表(anan_organization_auth)单体VO")
public class OrganizationAuthVO extends Id<Long> {
    private static final long serialVersionUID = 546516222560017033L;
    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "授权码")
    private String authorizationCode;

    @ApiModelProperty(value = "有效期：一般按天计算")
    private Integer validity;

    @ApiModelProperty(value = "到期后保护期")
    private Integer protectDays;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxOrganizs;

    @ApiModelProperty(value = "最大机构数：0=无限制 n=限制数")
    private Integer maxUsers;

    @ApiModelProperty(value = "是否试用：0=不试用 1=试用")
    private Integer tryout;

    @ApiModelProperty(value = "试用天数")
    private Integer tryoutDays;

}
