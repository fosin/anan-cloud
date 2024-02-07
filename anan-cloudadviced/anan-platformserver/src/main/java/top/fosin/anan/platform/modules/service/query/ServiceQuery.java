package top.fosin.anan.platform.modules.service.query;


import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "系统服务表(anan_service)通用查询DTO")
public class ServiceQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 564515506847161629L;

    @Schema(description = "服务标识", example = "String")
    private String code;

    @Schema(description = "服务名称", example = "String")
    private String name;

    @Schema(description = "状态码：0：禁用 1：启用", example = "Integer")
    private Byte status;

}
