package top.fosin.anan.platform.modules.service.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
/**
 * 系统服务表(anan_service)创建DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Schema(description = "系统服务表(anan_service)创建DTO")
public class ServiceCreateDTO extends LogiSortQuery<LogiQueryRule, SortRule, Integer> {
    private static final long serialVersionUID = 704294163969840463L;

    @NotBlank(message = "服务标识" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "服务标识", example = "String")
    private String code;

    @NotBlank(message = "服务名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "服务名称", example = "String")
    private String name;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "使用状态：0：禁用 1：启用", example = "0")
    private Byte status;

}
