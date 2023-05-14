package top.fosin.anan.platform.modules.service.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.cloudresource.grpc.service.UserGrpcServiceImpl;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
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
public class ServicePageVO extends Id<Long> {
    private static final long serialVersionUID = 178608658594077666L;
    @ApiModelProperty(value = "服务标识")
    private String code;

    @ApiModelProperty(value = "服务名称")
    private String name;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态码：0：禁用 1：启用")
    private Integer status;

}
