package top.fosin.anan.cloudresource.dto.req;

import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.LogicalQueryRule;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表(AnanUser)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:19
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统用户表查询DTO", description = "系统用户的查询DTO")
public class AnanUserRetrieveDto extends QuerySortRuleDto<LogicalQueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = -24340282458700184L;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "用户工号")
    private String usercode;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储")
    private String password;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15")
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统")
    private Date expireTime;


    @ApiModelProperty(value = "创建人", example = "1")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "2021-05-08 13:25:11")
    private Date createTime;

    @ApiModelProperty(value = "修改人", example = "1")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期", example = "2021-05-08 13:25:11")
    private Date updateTime;
}
