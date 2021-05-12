package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.TreeDto;

import java.io.Serializable;

/**
 * 系统机构表(AnanOrganization)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统机构表树形响应DTO", description = "表(anan_organization)的对应的创建DTO")
public class AnanOrganizationTreeDto extends TreeDto<AnanOrganizationTreeDto, Long> implements Serializable {
    private static final long serialVersionUID = 389815217019211695L;

    @ApiModelProperty(value = "顶级机构编号：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", required = true)
    private Long topId;

    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", required = true)
    private String code;

    @ApiModelProperty(value = "机构名称", required = true)
    private String name;

    @ApiModelProperty(value = "深度", required = true)
    private Integer level;

    @ApiModelProperty(value = "机构全名")
    private String fullname;

    @ApiModelProperty(value = "机构地址")
    private String address;

    @ApiModelProperty(value = "机构电话")
    private String telphone;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

}
