package com.github.fosin.cdp.platformapi.dto;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.12.5
 */
@Data
@ApiModel(value = "创建机构")
public class OrganizationRegisterDto implements Serializable {

    @NotBlank(message = "机构编码" + "{javax.validation.constraints.NotBlank.message}")
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", notes = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀")
    private String code;

    @NotBlank(message = "机构名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "机构名称", notes = "机构名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "机构全名", notes = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "机构全名不能包含特殊字符")
    private String fullname;

    @ApiModelProperty(value = "机构地址", notes = "机构地址")
    private String address;

    @ApiModelProperty(value = "机构电话", notes = "机构电话")
    private String telphone;

}
