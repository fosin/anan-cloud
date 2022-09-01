package top.fosin.anan.platform.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * 用户表修改密码的请求类
 *
 * @author fosin
 * @date 2022-9-1
 * @since 3.2.0
 */
@Data
@ToString
@ApiModel(value = "用户表修改密码的请求类", description = "用户表修改密码的请求类")
public class UserChangePassReq {
    private static final long serialVersionUID = -37913233914512798L;

    @ApiModelProperty(value = "用户ID,取值于User.id", required = true, example = "123456")
    @Positive
    private Long id;

    @ApiModelProperty(value = "原密码(未加密)", required = true, example = "123456")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "确认新密码1(未加密)", required = true, example = "123456")
    @NotBlank
    private String confirmPassword1;

    @ApiModelProperty(value = "确认新密码2(未加密)", required = true, example = "123456")
    @NotBlank
    private String confirmPassword2;

}
