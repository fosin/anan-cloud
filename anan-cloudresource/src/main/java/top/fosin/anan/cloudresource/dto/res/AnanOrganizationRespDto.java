package top.fosin.anan.cloudresource.dto.res;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.PidDto;

/**
 * 系统机构表(AnanOrganization)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统机构表响应DTO", description = "表(anan_organization)的响应DTO")
public class AnanOrganizationRespDto extends PidDto<Long> implements Serializable {
    private static final long serialVersionUID = 386478582280754215L;
    @ApiModelProperty(value = "顶级机构编码：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", example = "Long")
    private Long topId;

    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", example = "String")
    private String code;

    @ApiModelProperty(value = "机构名称", example = "String")
    private String name;

    @ApiModelProperty(value = "深度", example = "Integer")
    private Integer level;

    @ApiModelProperty(value = "机构全名", example = "String")
    private String fullname;

    @ApiModelProperty(value = "机构地址", example = "String")
    private String address;

    @ApiModelProperty(value = "机构电话", example = "String")
    private String telphone;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Integer status;

}
