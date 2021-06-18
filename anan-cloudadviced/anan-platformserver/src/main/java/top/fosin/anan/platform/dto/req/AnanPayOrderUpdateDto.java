package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.dto.IdDto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 支付订单表(AnanPayOrder)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "支付订单表更新DTO", description = "支付订单的更新DTO")
public class AnanPayOrderUpdateDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 376987885367918806L;

    @NotNull(message = "订单机构" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "订单机构", required = true)
    private Long organizId;

    @NotNull(message = "订单用户" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "订单用户", required = true)
    private Long userId;

    @NotNull(message = "版本ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本ID", required = true)
    private Long versionId;

    @NotNull(message = "版本金额" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本金额", required = true)
    private Double money;

    @ApiModelProperty(value = "订单时间", required = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @NotNull(message = "订单状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "订单状态：0=新建，1=支付，2=取消，3=作废", required = true)
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @ApiModelProperty(value = "取消时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date cancleTime;

    @ApiModelProperty(value = "作废时间")
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    private Date invalidTime;

}
