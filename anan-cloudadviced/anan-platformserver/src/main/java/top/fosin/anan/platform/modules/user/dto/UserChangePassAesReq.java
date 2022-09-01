package top.fosin.anan.platform.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * 用户表修改密码的请求类(加密方式)
 *
 * @author fosin
 * @date 2022-9-1
 * @since 3.2.0
 */
@Data
@ToString
@ApiModel(value = "用户表修改密码的请求类(加密方式)", description = "用户表修改密码的请求类(加密方式)")
public class UserChangePassAesReq {
    private static final long serialVersionUID = -37913233914512798L;

    @ApiModelProperty(value = "原密码(未加密)", required = true, example = "123456")
    @NotBlank
    private String a;

    @ApiModelProperty(value = "确认新密码1(未加密)", required = true, example = "123456")
    @NotBlank
    private String b;

    @ApiModelProperty(value = "passphrase口令", required = true, example = "123456")
    @NotBlank
    private String c;

    @ApiModelProperty(value = "iv密钥偏移量", required = true, example = "123456")
    @NotBlank
    private String d;

    @ApiModelProperty(value = "salt盐值", required = true, example = "123456")
    @NotBlank
    private String e;

    @ApiModelProperty(value = "keysize值大小", required = true, example = "128")
    @Positive
    private Integer f;

    @ApiModelProperty(value = "iterationCount密钥加密次数", required = true, example = "100")
    @Positive
    private Integer g;

    @ApiModelProperty(value = "确认新密码2(未加密)", required = true, example = "123456")
    @NotBlank
    private String h;

    @ApiModelProperty(value = "用户ID,取值于User.id", required = true, example = "123456")
    @Positive
    private Long i;

}
