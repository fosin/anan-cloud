package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.OrganizIdCreateUpdateDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表(AnanUser)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户表响应DTO", description = "用户的响应DTO")
public class AnanUserRespDto extends OrganizIdCreateUpdateDto<Long> implements Serializable {
    private static final long serialVersionUID = -37913233914512798L;

    @ApiModelProperty(value = "用户工号", example = "String")
    private String usercode;

    @ApiModelProperty(value = "用户姓名", example = "String")
    private String username;

    @ApiModelProperty(value = "顶级机构ID", example = "1")
    private Long topId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生日", example = DateTimeUtil.DATETIME_PATTERN)
    private Date birthday;

    @ApiModelProperty(value = "使用状态：具体取值于字典表anan_dictionary.code=15", example = "Integer")
    private Integer sex;

    @ApiModelProperty(value = "电子邮箱", example = "String")
    private String email;

    @ApiModelProperty(value = "手机号码", example = "String")
    private String phone;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "头像", example = "String")
    private String avatar;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统", example = DateTimeUtil.DATETIME_PATTERN)
    private Date expireTime;

}
