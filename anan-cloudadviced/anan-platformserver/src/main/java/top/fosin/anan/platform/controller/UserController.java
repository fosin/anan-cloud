package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.AnanUserRetrieveDto;
import top.fosin.anan.cloudresource.dto.res.AnanRoleRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRoleRespDto;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.model.controller.BaseController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.dto.IdDto;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platform.dto.req.AnanUserCreateDto;
import top.fosin.anan.platform.dto.req.AnanUserPermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanUserRoleCreateDto;
import top.fosin.anan.platform.dto.req.AnanUserUpdateDto;
import top.fosin.anan.platform.dto.res.AnanUserPermissionRespDto;
import top.fosin.anan.platform.service.inter.RoleService;
import top.fosin.anan.platform.service.inter.UserPermissionService;
import top.fosin.anan.platform.service.inter.UserRoleService;
import top.fosin.anan.platform.service.inter.UserService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * 用户控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.USER)
@Api(value = UrlPrefixConstant.USER, tags = "用户管理")
public class UserController extends BaseController implements ISimpleController<AnanUserRespDto,
        Long, AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final UserPermissionService userPermissionService;

    public UserController(UserService userService, UserRoleService userRoleService, RoleService roleService, UserPermissionService userPermissionService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.userPermissionService = userPermissionService;
    }

    @PostMapping("/usercode/{usercode}")
    @ApiImplicitParam(name = "usercode", value = "用户工号,取值于AnanUserEntity.usercode",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation("根据用户工号查找用户信息")
    public ResponseEntity<AnanUserRespDto> findOneByUsercode(@NotBlank @PathVariable("usercode") String usercode) {
        return ResponseEntity.ok(userService.findByUsercode(usercode));
    }

    @ApiOperation("修改用户帐号密码")
    @PostMapping("/changePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = TreeDto.ID_NAME, value = "参数类型,取值于AnanUserEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "原密码(未加密)",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "confirmPassword1", value = "确认新密码1(未加密)",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "confirmPassword2", value = "确认新密码2(未加密)",
                    required = true, dataTypeClass = String.class, paramType = "query")
    })
    public ResponseEntity<String> changePassword(@Min(1) @RequestParam(TreeDto.ID_NAME) Long id,
                                                 @NotBlank @RequestParam("password") String password,
                                                 @NotBlank @RequestParam("confirmPassword1") String confirmPassword1,
                                                 @NotBlank @RequestParam("confirmPassword2") String confirmPassword2) throws AnanControllerException, AnanServiceException {
        userService.changePassword(id, password, confirmPassword1, confirmPassword2);
        return ResponseEntity.ok("Success");
    }

    @ApiOperation("根据用户ID查询用户权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
                    required = true, dataTypeClass = String.class, paramType = "path"),
            @ApiImplicitParam(name = "organizId", value = "机构ID",
                    required = true, dataTypeClass = Long.class, paramType = "query")
    })
    @RequestMapping(value = "/permissions/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanUserPermissionRespDto>> permissions(@Min(1) @PathVariable Long userId,
                                                                       @RequestParam("organizId") Long organizId) {
        return ResponseEntity.ok(userPermissionService.findByUserIdAndOrganizId(userId, organizId));
    }

    @ApiOperation("根据用户ID更新用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户权限集合(List<AnanUserPermissionEntity>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path"),
    })
    @PutMapping(value = "/permissions/{userId}")
    public ResponseEntity<Collection<AnanUserPermissionRespDto>>
    permissions(@NotNull @RequestBody List<AnanUserPermissionCreateDto> entities,
                @Min(1) @PathVariable Long userId) {
        return ResponseEntity.ok(userPermissionService.updateInBatch("userId", userId, entities));
    }

    @ApiOperation(value = "根据用户ID重置用户密码", notes = "重置后的密码或是固定密码或是随机密码，具体由机构参数UserDefaultPasswordStrategy决定")
    @ApiImplicitParam(name = IdDto.ID_NAME, value = "用户ID,取值于AnanUserEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @PostMapping("/resetPassword/{id}")
    public ResponseEntity<String> resetPassword(@Min(1) @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.resetPassword(id).getPassword());
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息列表")
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/roles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRoleRespDto>> getUserRoles(@Min(1) @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(roleService.findRoleUsersByRoleId(userId));
    }

    @ApiOperation("更新用户拥有的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户角色集合(List<AnanUserRoleEntity>)",
                    required = true, dataTypeClass = List.class, paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")
    })
    @PutMapping(value = "/roles/{userId}")
    public ResponseEntity<Collection<AnanUserRoleRespDto>> putUserRoles
            (@NotNull @RequestBody List<AnanUserRoleCreateDto> entities,
             @Min(1) @PathVariable Long userId) throws
            AnanServiceException {
        return ResponseEntity.ok(userRoleService.updateInBatch("userId", userId, entities));
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息")
    @ApiImplicitParam(name = "userId", value = "用户ID,对应AnanRoleEntity.id",
            required = true, dataTypeClass = Integer.class, paramType = "path")
    @RequestMapping(value = "/otherRoles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRoleRespDto>> getOtherRoles(@Min(1) @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(roleService.findOtherUsersByRoleId(userId));
    }

    @PostMapping({"/list/organizId/{organizId}/{status}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有用户")
    @ApiImplicitParam(name = "organizId", value = "机构ID",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanUserRespDto>> listByOrganizId(@NotNull @PathVariable("organizId") Long organizId,
                                                                 @NotNull @PathVariable("status") Integer status) {
        return ResponseEntity.ok(userService.listByOrganizId(organizId, status));
    }

    @PostMapping({"/list/topId/{topId}/{status}"})
    @ApiOperation("根据顶级机构ID查询其下所有用户")
    @ApiImplicitParam(name = "topId", value = "顶级机构ID，传0表示默认查询当前用户的顶级机构ID",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanUserRespDto>> listAllChildByTopId(@NotNull @PathVariable("topId") Long topId,
                                                                     @NotNull @PathVariable("status") Integer status) {
        return ResponseEntity.ok(userService.listAllChildByTopId(topId, status));
    }

    @Override
    public UserService getService() {
        return userService;
    }
}
