package top.fosin.anan.platform.modules.pay.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统支付订单表(anan_pay_order)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付订单表(anan_pay_order)DTO")
public class PayOrderDTO extends Id<Long> {
    private static final long serialVersionUID = 430986997104560125L;

    @Schema(description = "订单机构", example = "Long")
    private Long organizId;

    @Schema(description = "订单用户", example = "Long")
    private Long userId;

    @Schema(description = "版本序号", example = "Long")
    private Long versionId;

    @Schema(description = "版本金额", example = "Double")
    private BigDecimal money;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "订单时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date orderTime;

    @Schema(description = "订单状态：0=新建，1=支付，2=取消，3=作废", example = "Integer")
    private Byte status;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "支付时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date payTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "取消时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date cancleTime;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "作废时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date invalidTime;

}
