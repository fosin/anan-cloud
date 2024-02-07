package top.fosin.anan.platform.modules.international.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;

import java.util.Date;

/**
 * 国际化语言字符集(anan_international_charset)DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "国际化语言字符集(anan_international_charset)DTO")
public class InternationalCharsetDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 357997136447228021L;
    @Schema(description = "国际化语言ID", example = "Integer")
    private Long internationalId;

    @Schema(description = "服务ID", example = "Integer")
    private Long serviceId;

    @Schema(description = "自定义字符集", example = "String")
    private String charset;

    @Schema(description = "创建人")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改人")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "状态：0=启用，1=禁用", example = "Integer")
    private Byte status;

    @Override
    public Long getFkValue() {
        return internationalId;
    }

    @Override
    public void setFkValue(Long fkValue) {
        this.internationalId = fkValue;
    }

    @Override
    public String getFkName() {
        return "internationalId";
    }
}
