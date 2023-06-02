package top.fosin.anan.platform.modules.service.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统服务表(anan_service)集合VO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统服务表集合VO", description = "系统服务表(anan_service)集合VO")
public class ServiceListVO extends Id<Long> {
    private static final long serialVersionUID = -54391460299837734L;
    @ApiModelProperty(value = "服务标识")
    private String code;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @ApiModelProperty(value = "状态码：0：禁用 1：启用")
    private Byte status;

}
