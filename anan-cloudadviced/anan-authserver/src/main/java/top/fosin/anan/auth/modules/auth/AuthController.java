package top.fosin.anan.auth.modules.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.auth.modules.auth.service.inter.AuthService;
import top.fosin.anan.auth.modules.auth.verifycode.VerifyCode;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.entity.UserAllPermissionTreeVO;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author fosin
 */
@RestController
@RequestMapping(PathPrefixConstant.API)
@AllArgsConstructor
@Tag(name = "获取认证相关信息", description = PathPrefixConstant.API)
public class AuthController {
    private final AuthService authService;

    @GetMapping("/vercode")
    @Operation(summary = "获取验证码", tags = "获取验证码", description = "获取验证码")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = req.getSession();
        session.setAttribute("index_code", text);
        VerifyCode.output(image, resp.getOutputStream());
    }

    @GetMapping(value = "/user/tree" + PathSuffixConstant.USER_ID)
    @Operation(summary = "查询用户权限树", description = "查询用户权限树")
    @Parameter(name = FieldConstant.USER_ID, description = "用户的唯一序号", in = ParameterIn.DEFAULT, required = true)
    public SingleResult<UserAllPermissionTreeVO> findTreeByUserId(@PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(authService.treeByUserId(userId));
    }

}
