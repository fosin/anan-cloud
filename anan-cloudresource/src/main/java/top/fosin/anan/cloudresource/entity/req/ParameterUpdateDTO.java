package top.fosin.anan.cloudresource.entity.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.*;

/**
 * 用于存放各种分类分组的个性化参数(anan_parameter)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "用于存放各种分类分组的个性化参数(anan_parameter)更新DTO")
public class ParameterUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -55459994981170238L;

    @Schema(description = "参数键")
    @NotBlank(message = "参数键{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Pattern(regexp = "[\\w]{1,64}", message = "参数键只能大小写字母、数字、下杠(_)组合而成,长度不超过64位", groups = Update.class)
    private String name;

    @Schema(description = "参数值")
    private String value;

    @Schema(description = "参数分类：具体取值于字典表anan_dictionary.code=10")
    @NotNull(message = "参数分类{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "参数分类{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    private Byte type;

    @Schema(description = "参数作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符", groups = Update.class)
    private String scope;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "参数描述")
    private String description;

}