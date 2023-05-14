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
 * 系统版本权限表(anan_version_permission)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统版本权限表单体VO", description = "系统版本权限表(anan_version_permission)单体VO")
public class VersionPermissionVO extends Id<Long> {
    private static final long serialVersionUID = -11097882846689491L;
    @ApiModelProperty(value = "版本ID")
    private Long versionId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}
