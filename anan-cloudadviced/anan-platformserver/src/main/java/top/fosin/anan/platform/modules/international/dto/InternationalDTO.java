package top.fosin.anan.platform.modules.international.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 国际化语言集(anan_international)DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "国际化语言集DTO", description = "国际化语言集(anan_international)DTO")
public class InternationalDTO extends Id<Long> {
    private static final long serialVersionUID = 918286250864754848L;
    @ApiModelProperty(value = "标识", required = true, example = "String")
    private String code;

    @ApiModelProperty(value = "名称", required = true, example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @ApiModelProperty(value = "${column.comment}", required = true, example = "Integer")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "${column.comment}", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "${column.comment}", required = true, example = "Integer")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "${column.comment}", required = true, example = "Date")
    private Date updateTime;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", required = true, example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "默认标志", required = true, example = "Integer")
    private Integer defaultFlag;

}
