package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

/**
 * 支付明细表(AnanPayDetail)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "支付明细表响应DTO", description = "支付明细的响应DTO")
public class AnanPayDetailRespDto extends IdDto<Long> {
    private static final long serialVersionUID = 333646482553193487L;
    @ApiModelProperty(value = "支付序号", example = "Long")
    private Long payId;

    @ApiModelProperty(value = "付款方式", example = "Integer")
    private Integer payway;

    @ApiModelProperty(value = "付款金额", example = "Double")
    private Double money;

}
