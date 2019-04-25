package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.anan.util.DateTimeUtil;

/**
 * 系统支付明细表(AnanPayDetail)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统支付明细表查询DTO", description = "表(anan_pay_detail)的对应的查询DTO")
public class AnanPayDetailRetrieveDto implements Serializable {
    private static final long serialVersionUID = 318623987516262809L;
    
    @ApiModelProperty(value = "付款明细ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "支付ID", example = "Long")
    private Long payId;

    @ApiModelProperty(value = "付款方式", example = "Integer")
    private Integer payway;

    @ApiModelProperty(value = "付款金额", example = "Double")
    private Double money;

}