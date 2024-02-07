package top.fosin.anan.cloudresource.entity.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import top.fosin.anan.core.util.RegexUtil;

import java.io.Serializable;

/**
 * @author fosin
 * @date 2018.12.5
 */
@Data
@Schema(description = "创建机构")
public class OrgRegisterReqDTO implements Serializable {

    private static final long serialVersionUID = -2691405300807660308L;
    @NotBlank(message = "机构编码" + "{jakarta.validation.constraints.NotBlank.message}")
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    @Schema(description = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀")
    private String code;

    @NotBlank(message = "机构名称" + "{jakarta.validation.constraints.NotBlank.message}")
    @Schema(description = "机构名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构名称不能包含特殊字符")
    private String name;

    @Schema(description = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构全名不能包含特殊字符")
    private String fullname;

    @Schema(description = "机构地址")
    private String address;

    @Schema(description = "机构电话")
    private String telphone;

}
