package top.fosin.anan.platform.modules.version.dto;

                                                                                                                                                                                    

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
 * 系统版本角色表(anan_version_role)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统版本角色表DTO", description = "系统版本角色表(anan_version_role)DTO")
public class VersionRoleDTO extends Id<Long> {
    private static final long serialVersionUID = -39772280545805808L;
    @ApiModelProperty(value = "版本ID", required = true, example = "Long")
    private Long versionId;

    @ApiModelProperty(value = "角色名称", required = true, example = "String")
    private String name;

    @ApiModelProperty(value = "角色标识", required = true, example = "String")
    private String value;

    @ApiModelProperty(value = "角色说明", example = "String")
    private String tips;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true, example = "Integer")
    private Integer status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改时间", required = true, example = "Date")
    private Date updateTime;

    @ApiModelProperty(value = "修改人", required = true, example = "Long")
    private Long updateBy;

}
