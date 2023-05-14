package top.fosin.anan.platform.modules.user.dto;


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
 * 系统用户角色表(anan_user_role)DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统用户角色表DTO", description = "系统用户角色表(anan_user_role)DTO")
public class UserRoleDTO extends Id<Long> {
    private static final long serialVersionUID = 841899805227236995L;
    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户ID", required = true, example = "Long")
    private Long userId;

    @ApiModelProperty(value = "角色ID", required = true, example = "Long")
    private Long roleId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

}
