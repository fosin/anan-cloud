package top.fosin.anan.platform.modules.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * 用户表修改密码的请求类
 *
 * @author fosin
 * @date 2022-9-1
 * @since 3.2.0
 */
@Data
@ToString
@Schema(description = "用户表修改密码的请求类")
public class UserChangePassReq {
    private static final long serialVersionUID = -37913233914512798L;

    @Schema(description = "用户ID,取值于User.id", example = "123456")
    @Positive
    private Long id;

    @Schema(description = "原密码(未加密)", example = "123456")
    @NotBlank
    private String password;

    @Schema(description = "确认新密码1(未加密)", example = "123456")
    @NotBlank
    private String confirmPassword1;

    @Schema(description = "确认新密码2(未加密)", example = "123456")
    @NotBlank
    private String confirmPassword2;

}
