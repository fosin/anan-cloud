package top.fosin.anan.platform.modules.version.vo;

                                                                                                                                                                                                                                                                                                            
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
 * 系统版本表(anan_version)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统版本表单体VO", description = "系统版本表(anan_version)单体VO")
public class VersionVO extends Id<Long> {
    private static final long serialVersionUID = 348227108096797162L;
    @ApiModelProperty(value = "版本名称")
    private String name;

    @ApiModelProperty(value = "版本类型：0=收费版 1=免费版 2=开发版")
    private Integer type;

    @ApiModelProperty(value = "版本价格")
    private Double price;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动开始日期")
    private Date beginTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "活动结束日期")
    private Date endTime;

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

    @ApiModelProperty(value = "启用状态：0=启用，1=禁用")
    private Integer status;

    @ApiModelProperty(value = "版本描述")
    private String description;

}
