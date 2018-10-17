package com.starlight.cdp.platform.controller;

import com.starlight.cdp.core.exception.CdpControllerException;
import com.starlight.cdp.core.exception.CdpServiceException;
import com.starlight.cdp.mvc.controller.AbstractBaseController;
import com.starlight.cdp.mvc.controller.ISimpleController;
import com.starlight.cdp.mvc.service.ISimpleService;
import com.starlight.cdp.platformapi.entity.CdpSysRoleEntity;
import com.starlight.cdp.platformapi.entity.CdpSysUserEntity;
import com.starlight.cdp.platformapi.entity.CdpSysUserPermissionEntity;
import com.starlight.cdp.platformapi.entity.CdpSysUserRoleEntity;
import com.starlight.cdp.platformapi.service.inter.IRoleService;
import com.starlight.cdp.platformapi.service.inter.IUserPermissionService;
import com.starlight.cdp.platformapi.service.inter.IUserRoleService;
import com.starlight.cdp.platformapi.service.inter.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description 用户控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/user")
@Api(value = "v1/user",tags = "用户管理", description = "用户管理相关操作")
public class UserController extends AbstractBaseController implements ISimpleController<CdpSysUserEntity, Integer> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserPermissionService userPermissionService;

    @GetMapping("/usercode/{usercode}")
    @ApiImplicitParam(name = "usercode", value = "用户工号,取值于CdpSysUserEntity.usercode")
    @ApiOperation("根据用户工号查找用户信息")
    public ResponseEntity<CdpSysUserEntity> getByUsercode(@PathVariable("usercode") String usercode) {
        return ResponseEntity.ok(userService.findByUsercode(usercode));
    }

    @ApiOperation("修改用户帐号密码")
    @PostMapping("/changePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数类型,取值于CdpSysUserEntity.id"),
            @ApiImplicitParam(name = "password", value = "原密码(未加密)"),
            @ApiImplicitParam(name = "confirmPassword1", value = "确认新密码1(未加密)"),
            @ApiImplicitParam(name = "confirmPassword2", value = "确认新密码2(未加密)")
    })
    public ResponseEntity<String> changePassword(@RequestParam("id") Integer id,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("confirmPassword1") String confirmPassword1,
                                                 @RequestParam("confirmPassword2") String confirmPassword2) throws CdpControllerException, CdpServiceException {
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
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于CdpSysUserEntity.id")
    @RequestMapping(value = "/permissions/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpSysUserPermissionEntity>> permissions(@PathVariable Integer userId) {
        return ResponseEntity.ok(userPermissionService.findByUserId(userId));
    }

    @ApiOperation("根据用户ID更新用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户权限集合(List<CdpSysUserPermissionEntity>)"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于CdpSysUserEntity.id")
    })
    @PutMapping(value = "/permissions/{userId}")
    public ResponseEntity<List<CdpSysUserPermissionEntity>> permissions(@RequestBody List<CdpSysUserPermissionEntity> entities, @PathVariable Integer userId) throws CdpServiceException {
        return ResponseEntity.ok(userPermissionService.updateInBatch(userId, entities));
    }

    @ApiOperation(value = "根据用户ID重置用户密码", notes = "重置后的密码或是固定密码或是随机密码，具体由机构参数UserResetPasswordType决定")
    @ApiImplicitParam(name = "id", value = "用户ID,取值于CdpSysUserEntity.id")
    @PostMapping("/resetPassword/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable() Integer id) throws CdpServiceException {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息列表")
    @ApiImplicitParam(name = "userId", value = "用户ID,取值于CdpSysUserEntity.id")
    @RequestMapping(value = "/roles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpSysRoleEntity>> getUserRoles(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(roleService.findRoleUsersByRoleId(userId));
    }

    @ApiOperation("更新用户拥有的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户角色集合(List<CdpSysUserRoleEntity>)"),
            @ApiImplicitParam(name = "userId", value = "用户ID,取值于CdpSysUserEntity.id")
    })
    @PutMapping(value = "/roles/{userId}")
    public ResponseEntity<List<CdpSysUserRoleEntity>> putRoles
            (@RequestBody List<CdpSysUserRoleEntity> entities, @PathVariable Integer userId) throws
            CdpServiceException {
        return ResponseEntity.ok(userRoleService.updateInBatchByUserId(userId, entities));
    }

    @ApiOperation("根据用户唯一id查找用户所有角色信息")
    @ApiImplicitParam(name = "userId", value = "用户ID,对应CdpSysRoleEntity.id", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/otherRoles/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CdpSysRoleEntity>> getOtherRoles(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(roleService.findOtherUsersByRoleId(userId));
    }

    @Override
    public ISimpleService<CdpSysUserEntity, Integer> getService() {
        return userService;
    }
}
