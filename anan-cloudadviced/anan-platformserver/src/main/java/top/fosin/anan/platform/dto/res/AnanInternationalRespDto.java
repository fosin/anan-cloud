package top.fosin.anan.platform.dto.res;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

/**
 * 国际化语言集(AnanInternational)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际化语言集响应DTO", description = "表(anan_international)的响应DTO")
public class AnanInternationalRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 589462762626564199L;
    @ApiModelProperty(value = "标识", example = "String")
    private String code;

    @ApiModelProperty(value = "名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "默认标志", example = "Integer")
    private Integer defaultFlag;

}
