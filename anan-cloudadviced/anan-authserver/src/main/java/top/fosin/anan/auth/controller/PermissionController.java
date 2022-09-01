package top.fosin.anan.auth.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.dto.UserAllPermissionTreeDto;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;

/**
 * @author fosin
 * @date 2018.8.22
 */
@RestController
@ApiIgnore
@RequestMapping(value = PathPrefixConstant.PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
public class PermissionController {
    private final AuthService authService;

    public PermissionController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/user/tree" + PathSuffixConstant.USER_ID, method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询用户权限树", notes = "查询用户权限树")
    @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户的唯一序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public SingleResult<UserAllPermissionTreeDto> findTreeByUserId(@PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(authService.treeByUserId(userId));
    }

}
