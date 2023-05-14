package top.fosin.anan.platform.modules.user.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.module.LogiQueryRule;
import java.util.Date;

        /**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限通用查询DTO", description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)通用查询DTO")
public class UserPermissionQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 383601033466014740L;
    
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户ID", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", example = "Integer")
    private Integer addMode;

}
