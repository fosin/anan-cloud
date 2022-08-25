package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.res.IdCreateUpdateDto;

/**
 * 系统服务表(AnanService)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "服务表响应DTO", description = "服务的响应DTO")
public class ServiceRespDto extends IdCreateUpdateDto<Long> {
    private static final long serialVersionUID = -56691240580744332L;
    @ApiModelProperty(value = "服务标识", example = "String")
    private String code;

    @ApiModelProperty(value = "服务名称", example = "String")
    private String name;

    @ApiModelProperty(value = "状态码：0：禁用 1：启用", example = "Integer")
    private Integer status;

}
