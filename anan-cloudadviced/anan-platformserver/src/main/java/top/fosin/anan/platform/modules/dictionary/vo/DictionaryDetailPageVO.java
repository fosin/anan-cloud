package top.fosin.anan.platform.modules.dictionary.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.cloudresource.grpc.service.UserGrpcServiceImpl;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.converter.translate.Translate2String;
import top.fosin.anan.data.entity.Id;

import java.util.Date;

        /**
 * 系统通用字典明细表(anan_dictionary_detail)集合VO
 *
 * @author fosin
 * @date 2023-05-21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统通用字典明细表(anan_dictionary_detail)集合VO")
public class DictionaryDetailPageVO extends Id<Long> {
    private static final long serialVersionUID = 597868481094061497L;
    @Schema(description = "取值于字典表anan_dictionary.id")
    private Long dictionaryId;

    @Schema(description = "字典明细键，不能重复，字典内明细项唯一代码")
    private Long code;

    @Schema(description = "字典明细字面说明")
    private String name;

    @Schema(description = "顺序，用于显示数据时的顺序，数值越小越靠前")
    private Short sort;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @Schema(description = "标准代码，该字段通常用于对接标准字典")
    private String scode;

    @Schema(description = "作用域，用于字典明细项的作用域")
    private String scope;

    @Schema(description = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性")
    private Byte used;

    @Schema(description = "详细说明")
    private String description;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @Schema(description = "创建人")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期")
    private Date createTime;

    @Translate2String(service = UserGrpcServiceImpl.class, dicId = "")
    @Schema(description = "修改人")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改日期")
    private Date updateTime;

}
