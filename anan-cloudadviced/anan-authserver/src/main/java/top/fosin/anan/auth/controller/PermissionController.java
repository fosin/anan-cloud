package top.fosin.anan.auth.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.UserAllPermissionTreeDto;

/**
 * @author fosin
 * @date 2018.8.22
 */
@RestController
@ApiIgnore
@RequestMapping(UrlPrefixConstant.PERMISSION)
public class PermissionController {
    private final AuthService authService;

    public PermissionController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/user/tree/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询用户权限树", notes = "查询用户权限树")
    @ApiImplicitParam(name = "userId", value = "用户的唯一序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<UserAllPermissionTreeDto> findTreeByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(authService.treeByUserId(userId));
    }

}
