package top.fosin.anan.platform.modules.dictionary.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 系统通用字典明细表(anan_dictionary_detail)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统通用字典明细表(anan_dictionary_detail)创建DTO")
public class DictionaryDetailCreateDTO {
    private static final long serialVersionUID = -46164990948316096L;
    
    @NotNull(message = "字典序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "字典序号，取值于字典表anan_dictionary.id", example = "Long")
    private Long dictionaryId;

    @NotNull(message = "字典明细键" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "字典明细键", example = "Long")
    private Long code;

    @Schema(description = "字典明细字面说明", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "字典明细值不能包含特殊字符", groups = Create.class)
    private String name;

    @NotNull(message = "顺序" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "顺序，用于显示数据时的顺序，数值越小越靠前", example = "Short")
    private Short sort;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Byte")
    private Byte status;

    @Schema(description = "标准代码，该字段通常用于对接标准字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "标准代码不能包含特殊字符", groups = Create.class)
    private String scode;

    @Schema(description = "作用域，用于字典明细项的作用域", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符", groups = Create.class)
    private String scope;

    @NotNull(message = "使用标志" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", example = "Byte")
    private Byte used;

    @Schema(description = "详细说明", example = "String")
    private String description;

}
