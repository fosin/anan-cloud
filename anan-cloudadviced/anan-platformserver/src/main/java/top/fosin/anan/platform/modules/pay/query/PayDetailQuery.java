package top.fosin.anan.platform.modules.pay.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import java.math.BigDecimal;

/**
 * 系统支付明细表(anan_pay_detail)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统支付明细表(anan_pay_detail)通用查询DTO")
public class PayDetailQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 750228515695366443L;

    @Schema(description = "支付ID", example = "Long")
    private Long payId;

    @Schema(description = "付款方式", example = "Integer")
    private Integer payway;

    @Schema(description = "付款金额", example = "Double")
    private BigDecimal money;

}
