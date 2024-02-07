package top.fosin.anan.platform.modules.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * 用户表修改密码的请求类(加密方式)
 *
 * @author fosin
 * @date 2022-9-1
 * @since 3.2.0
 */
@Data
@ToString
@Schema(description = "用户表修改密码的请求类(加密方式)")
public class UserChangePassAesReq {
    private static final long serialVersionUID = -37913233914512798L;

    @Schema(description = "原密码(未加密)", example = "123456")
    @NotBlank
    private String a;

    @Schema(description = "确认新密码1(未加密)", example = "123456")
    @NotBlank
    private String b;

    @Schema(description = "passphrase口令", example = "123456")
    @NotBlank
    private String c;

    @Schema(description = "iv密钥偏移量", example = "123456")
    @NotBlank
    private String d;

    @Schema(description = "salt盐值", example = "123456")
    @NotBlank
    private String e;

    @Schema(description = "keysize值大小", example = "128")
    @Positive
    private Integer f;

    @Schema(description = "iterationCount密钥加密次数", example = "100")
    @Positive
    private Integer g;

    @Schema(description = "确认新密码2(未加密)", example = "123456")
    @NotBlank
    private String h;

    @Schema(description = "用户ID,取值于User.id", example = "123456")
    @Positive
    private Long i;

}
