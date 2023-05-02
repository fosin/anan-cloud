package top.fosin.anan.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.auth.verifycode.VerifyCode;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.entity.UserAllPermissionTreeVO;
import top.fosin.anan.cloudresource.entity.UserDetail;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;

/**
 * @author fosin
 */
@RestController
@RequestMapping(PathPrefixConstant.API)
@AllArgsConstructor
@Api(value = PathPrefixConstant.API, tags = "获取认证相关信息")
public class AuthController {
    private CurrentUserService currentUserService;
    private final AuthService authService;

    @GetMapping(value = "/userdetail")
    @ApiOperation(value = "根据令牌获取当前认证用户信息", notes = "根据当前认证用户,获取认证本体信息")
    public SingleResult<UserDetail> principal(Principal principal) {
        return ResultUtils.success(currentUserService.getUserDetail());
    }

    @GetMapping("/vercode")
    @ApiOperation(value = "获取验证码", tags = "获取验证码")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = req.getSession();
        session.setAttribute("index_code", text);
        VerifyCode.output(image, resp.getOutputStream());
    }

    @GetMapping(value = "/user/tree" + PathSuffixConstant.USER_ID)
    @ApiOperation(value = "查询用户权限树", notes = "查询用户权限树")
    @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户的唯一序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public SingleResult<UserAllPermissionTreeVO> findTreeByUserId(@PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(authService.treeByUserId(userId));
    }

}
