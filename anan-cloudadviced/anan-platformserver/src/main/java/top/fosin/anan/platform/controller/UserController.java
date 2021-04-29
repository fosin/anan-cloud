package top.fosin.anan.platform.controller;

import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.request.*;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.model.controller.AbstractBaseController;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.service.ISimpleService;
import top.fosin.anan.platform.service.inter.RoleService;
import top.fosin.anan.platform.service.inter.UserPermissionService;
import top.fosin.anan.platform.service.inter.UserRoleService;
import top.fosin.anan.platform.service.inter.UserService;
import top.fosin.anan.platformapi.entity.AnanRoleEntity;
import top.fosin.anan.platformapi.entity.AnanUserEntity;
import top.fosin.anan.platformapi.entity.AnanUserPermissionEntity;
import top.fosin.anan.platformapi.entity.AnanUserRoleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Description 用户控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.USER)
@Api(value = UrlPrefixConstant.USER, tags = "用户管理")
public class UserController extends AbstractBaseController implements ISimpleController<AnanUserEntity, Long, AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> {
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
    public ResponseEntity<AnanUserEntity> getByUsercode(@PathVariable("usercode") String usercode) {
        return ResponseEntity.ok(userService.findByUsercode(usercode));
    }

    @ApiOperation("修改用户帐号密码")
    @PostMapping("/changePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SystemConstant.ID_NAME, value = "参数类型,取值于AnanUserEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "原密码(未加密)",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "confirmPassword1", value = "确认新密码1(未加密)",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "confirmPassword2", value = "确认新密码2(未加密)",
                    required = true, dataTypeClass = String.class, paramType = "query"),
    })
    public ResponseEntity<String> changePassword(@RequestParam(SystemConstant.ID_NAME) Long id,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("confirmPassword1") String confirmPassword1,
                                                 @RequestParam("confirmPassword2") String confirmPassword2) throws AnanControllerException, AnanServiceException {
        userService.changePassword(id, password, confirmPassword1, confirmPassword2);
        return ResponseEntity.ok("");
    }

    //    @RequestMapping("/changePassword")
//    public ResponseEntity<String> changePassword(@RequestParam("a") String password,
//                                                 @RequestParam("b") String confirmpassword1,
//                                                 @RequestParam("c") String passphrase,
//                                                 @RequestParam("d") String iv,
//                                                 @RequestParam("e") String salt,
//                                                 @RequestParam("f") Integer keysize,
//                                                 @RequestParam("g") Integer iterationcount,
//                                                 @RequestParam("h") String confirmpassword2,
//                                                 @RequestParam("i") Integer id
//                                                ) {
//        AesUtil aesUtil = new AesUtil(keysize, iterationcount);
//        userService.changePassword(id, aesUtil.decrypt(salt, iv, passphrase, password),
//                aesUtil.decrypt(salt, iv, passphrase, confirmpassword1), aesUtil.decrypt(salt, iv, passphrase, confirmpassword2));
//        return ResponseEntity.ok("");
//    }

    @ApiOperation("根据用户ID查询用户权限列表")
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
            required = true, dataTypeClass = String.class, paramType = "query")
    @RequestMapping(value = "/permissions/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanUserPermissionEntity>> permissions(@PathVariable Long userId) {
        return ResponseEntity.ok(userPermissionService.findByUserId(userId));
    }

    @ApiOperation("根据用户ID更新用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户权限集合(List<AnanUserPermissionEntity>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
                    required = true, dataTypeClass = Long.class, paramType = "path"),
    })
    @PutMapping(value = "/permissions/{userId}")
    public ResponseEntity<Collection<AnanUserPermissionEntity>> permissions(@RequestBody List<AnanUserPermissionUpdateDto> entities, @PathVariable Long userId) {
        return ResponseEntity.ok(userPermissionService.updateInBatch(userId, entities));
    }

    @ApiOperation(value = "根据用户ID重置用户密码", notes = "重置后的密码或是固定密码或是随机密码，具体由机构参数UserResetPasswordType决定")
    @ApiImplicitParam(name = SystemConstant.ID_NAME, value = "用户ID,取值于AnanUserEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @PostMapping("/resetPassword/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable() Long id) {
        return ResponseEntity.ok(userService.resetPassword(id).getPassword());
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息列表")
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = "/roles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRoleEntity>> getUserRoles(@PathVariable("userId") Long userId) {
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
    public ResponseEntity<List<AnanUserRoleEntity>> putUserRoles
            (@RequestBody List<AnanUserRoleCreateDto> entities, @PathVariable Long userId) throws
            AnanServiceException {
        return ResponseEntity.ok(userRoleService.updateInBatchByUserId(userId, entities));
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息")
    @ApiImplicitParam(name = "userId", value = "用户ID,对应AnanRoleEntity.id",
            required = true, dataTypeClass = Integer.class, paramType = "path")
    @RequestMapping(value = "/otherRoles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRoleEntity>> getOtherRoles(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(roleService.findOtherUsersByRoleId(userId));
    }

    @PostMapping({"/childList/organizId/{organizId}/{status}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有用户")
    @ApiImplicitParam(name = "organizId", value = "机构ID",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanUserEntity>> findAllByOrganizId(@PathVariable("organizId") Long organizId, @PathVariable("status") Integer status) {
        return ResponseEntity.ok(userService.findAllByOrganizId(organizId, status));
    }

    @Override
    public ISimpleService<AnanUserEntity, Long, AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> getService() {
        return userService;
    }
}
