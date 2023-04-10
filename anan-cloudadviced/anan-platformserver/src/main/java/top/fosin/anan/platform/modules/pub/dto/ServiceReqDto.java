package top.fosin.anan.platform.modules.pub.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 系统服务表(AnanService)请求DTO
 *
 * @author fosin
 * @date 2020-12-04 17:48:21
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "服务表请求DTO", description = "服务的请求DTO")
public class ServiceReqDto extends IdLogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -95213722807513308L;

    @NotBlank(message = "服务标识" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "服务标识", example = "String")
    private String code;

    @NotBlank(message = "服务名称" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "服务名称", example = "String")
    private String name;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0：禁用 1：启用", example = "0")
    private Integer status;


}
