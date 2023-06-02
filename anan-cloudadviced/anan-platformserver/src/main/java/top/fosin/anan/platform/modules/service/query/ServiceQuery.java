package top.fosin.anan.platform.modules.service.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

/**
 * 系统服务表(anan_service)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统服务表通用查询DTO", description = "系统服务表(anan_service)通用查询DTO")
public class ServiceQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 564515506847161629L;

    @ApiModelProperty(value = "服务标识", example = "String")
    private String code;

    @ApiModelProperty(value = "服务名称", example = "String")
    private String name;

    @ApiModelProperty(value = "状态码：0：禁用 1：启用", example = "Integer")
    private Byte status;

}
