package com.github.fosin.cdp.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统支付明细表(CdpPayDetail)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付明细表更新DTO", description = "表(cdp_pay_detail)的对应的更新DTO")
public class CdpPayDetailUpdateDto implements Serializable {
    private static final long serialVersionUID = -10369919475947842L;

    @NotNull
    @ApiModelProperty(value = "付款明细ID", example = "Long", required = true)
    private Long paydetailId;

    @NotNull
    @ApiModelProperty(value = "支付ID", example = "Long", required = true)
    private Long payId;

    @NotNull
    @ApiModelProperty(value = "付款方式", example = "Integer", required = true)
    private Integer payway;

    @NotNull
    @ApiModelProperty(value = "付款金额", example = "Double", required = true)
    private Double money;

}
