package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.core.exception.AnanControllerException;
import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.mvc.controller.AbstractBaseController;
import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.platformapi.dto.request.*;
import com.github.fosin.anan.platformapi.entity.AnanRoleEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserPermissionEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserRoleEntity;
import com.github.fosin.anan.platform.service.inter.RoleService;
import com.github.fosin.anan.platform.service.inter.UserPermissionService;
import com.github.fosin.anan.platform.service.inter.UserRoleService;
import com.github.fosin.anan.platform.service.inter.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("v1/user")
@Api(value = "v1/user", tags = "用户管理", description = "用户管理相关操作")
public class UserController extends AbstractBaseController implements ISimpleController<AnanUserEntity, Long, AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserPermissionService userPermissionService;

    @PostMapping("/usercode/{usercode}")
    @ApiImplicitParam(name = "usercode", value = "用户工号,取值于AnanUserEntity.usercode")
    @ApiOperation("根据用户工号查找用户信息")
    public ResponseEntity<AnanUserEntity> getByUsercode(@PathVariable("usercode") String usercode) {
        return ResponseEntity.ok(userService.findByUsercode(usercode));
    }

    @ApiOperation("修改用户帐号密码")
    @PostMapping("/changePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数类型,取值于AnanUserEntity.id"),
            @ApiImplicitParam(name = "password", value = "原密码(未加密)"),
            @ApiImplicitParam(name = "confirmPassword1", value = "确认新密码1(未加密)"),
            @ApiImplicitParam(name = "confirmPassword2", value = "确认新密码2(未加密)")
    })
    public ResponseEntity<String> changePassword(@RequestParam("id") Long id,
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
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id")
    @RequestMapping(value = "/permissions/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanUserPermissionEntity>> permissions(@PathVariable Long userId) {
        return ResponseEntity.ok(userPermissionService.findByUserId(userId));
    }

    @ApiOperation("根据用户ID更新用户权限")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "用户权限集合(List<AnanUserPermissionEntity>)"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id")
    })
    @PutMapping(value = "/permissions/{userId}")
    public ResponseEntity<Collection<AnanUserPermissionEntity>> permissions(@RequestBody List<AnanUserPermissionUpdateDto> entities, @PathVariable Long userId) {
        return ResponseEntity.ok(userPermissionService.updateInBatch(userId, entities));
    }

    @ApiOperation(value = "根据用户ID重置用户密码", notes = "重置后的密码或是固定密码或是随机密码，具体由机构参数UserResetPasswordType决定")
    @ApiImplicitParam(name = "id", value = "用户ID,取值于AnanUserEntity.id")
    @PostMapping("/resetPassword/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable() Long id) {
        return ResponseEntity.ok(userService.resetPassword(id).getPassword());
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息列表")
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id")
    @RequestMapping(value = "/roles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRoleEntity>> getUserRoles(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(roleService.findRoleUsersByRoleId(userId));
    }

    @ApiOperation("更新用户拥有的角色")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "entities", value = "用户角色集合(List<AnanUserRoleEntity>)"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于AnanUserEntity.id")
    })
    @PutMapping(value = "/roles/{userId}")
    public ResponseEntity<List<AnanUserRoleEntity>> putUserRoles
            (@RequestBody List<AnanUserRoleCreateDto> entities, @PathVariable Long userId) throws
            AnanServiceException {
        return ResponseEntity.ok(userRoleService.updateInBatchByUserId(userId, entities));
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息")
    @ApiImplicitParam(name = "userId", value = "用户ID,对应AnanRoleEntity.id", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/otherRoles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<AnanRoleEntity>> getOtherRoles(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(roleService.findOtherUsersByRoleId(userId));
    }

    @PostMapping({"/childList/organizId/{organizId}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有用户")
    @ApiImplicitParam(name = "organizId", value = "机构ID")
    public ResponseEntity<List<AnanUserEntity>> findAllByOrganizId(@PathVariable("organizId") Long organizId) {
        return ResponseEntity.ok(userService.findAllByOrganizId(organizId));
    }

    @Override
    public ISimpleService<AnanUserEntity, Long, AnanUserCreateDto, AnanUserRetrieveDto, AnanUserUpdateDto> getService() {
        return userService;
    }
}
