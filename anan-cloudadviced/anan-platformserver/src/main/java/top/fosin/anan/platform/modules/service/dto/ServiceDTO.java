package top.fosin.anan.platform.modules.service.dto;


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
 * 系统服务表(anan_service)DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统服务表DTO", description = "系统服务表(anan_service)DTO")
public class ServiceDTO extends Id<Long> {
    private static final long serialVersionUID = -90160903658912682L;
    @ApiModelProperty(value = "服务标识", required = true, example = "String")
    private String code;

    @ApiModelProperty(value = "服务名称", required = true, example = "String")
    private String name;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "修改人", required = true, example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改时间", required = true, example = "Date")
    private Date updateTime;

    @ApiModelProperty(value = "状态码：0：禁用 1：启用", required = true, example = "Integer")
    private Integer status;

}
