package top.fosin.anan.platform.modules.international.dto;



import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "国际化语言集(anan_international)DTO")
public class InternationalDTO extends Id<Long> {
    private static final long serialVersionUID = 918286250864754848L;
    @Schema(description = "标识", example = "String")
    private String code;

    @Schema(description = "名称", example = "String")
    private String name;

    @Schema(description = "图标", example = "String")
    private String icon;

    @Schema(description = "${column.comment}", example = "Integer")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "${column.comment}", example = "Date")
    private Date createTime;

    @Schema(description = "${column.comment}", example = "Integer")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "${column.comment}", example = "Date")
    private Date updateTime;

    @Schema(description = "状态：0=启用，1=禁用", example = "Integer")
    private Byte status;

    @Schema(description = "默认标志", example = "Integer")
    private Integer defaultFlag;

}
