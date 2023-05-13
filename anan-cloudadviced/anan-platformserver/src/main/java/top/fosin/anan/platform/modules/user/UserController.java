package top.fosin.anan.platform.modules.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.entity.req.UserCreateDTO;
import top.fosin.anan.cloudresource.entity.req.UserUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.RoleRespDTO;
import top.fosin.anan.cloudresource.entity.res.UserRespDTO;
import top.fosin.anan.cloudresource.entity.res.UserRoleRespDTO;
import top.fosin.anan.core.util.crypt.AesUtil;
import top.fosin.anan.data.constant.PathConstant;
import top.fosin.anan.data.controller.*;
import top.fosin.anan.data.entity.req.PageQuery;
import top.fosin.anan.data.prop.IdProp;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.PageResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.role.service.inter.RoleService;
import top.fosin.anan.platform.modules.user.dto.*;
import top.fosin.anan.platform.modules.user.query.UserQuery;
import top.fosin.anan.platform.modules.user.service.inter.UserPermissionService;
import top.fosin.anan.platform.modules.user.service.inter.UserRoleService;
import top.fosin.anan.platform.modules.user.service.inter.UserService;
import top.fosin.anan.platform.modules.user.vo.UserListVO;
import top.fosin.anan.platform.modules.user.vo.UserPageVO;
import top.fosin.anan.platform.modules.user.vo.UserVO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.USER, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.USER, tags = "用户管理")
@AllArgsConstructor
public class UserController extends BaseController
        implements ICreateController<UserCreateDTO, Long>,
        IUpdateController<UserUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<UserQuery, UserVO, UserListVO, UserPageVO, Long> {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final UserPermissionService userPermissionService;

    @GetMapping(PathSuffixConstant.USER_CODE)
    @ApiImplicitParam(name = FieldConstant.USER_CODE, value = "用户工号,取值于User.usercode",
            required = true, dataTypeClass = String.class, paramType = "path")
    @ApiOperation("根据用户工号查找用户信息")
    public SingleResult<UserRespDTO> findOneByUsercode(@PathVariable(FieldConstant.USER_CODE) String usercode) {
        return ResultUtils.success(userService.findOneByUsercode(usercode));
    }

    @Override
    @PostMapping(value = PathConstant.PATH_PAGE)
    @ApiOperation(value = "根据分页条件查询分页数据")
    @ApiImplicitParam(name = "pageQuery", value = "分页条件实体类", paramType = "body", required = true, dataTypeClass = PageQuery.class)
    public PageResult<UserPageVO> findPage(PageQuery<UserQuery> pageQuery) {
        PageResult<UserPageVO> page = IRetrieveController.super.findPage(pageQuery);
        page.getData().forEach(user -> user.setOrganizName(user.getOrganizId() + ""));
        return page;
    }

    @ApiOperation("修改用户帐号密码")
    @PostMapping("/changePassword")
    @ApiImplicitParam(name = "passReq", value = "修改密码请求参数",
            required = true, dataTypeClass = UserChangePassAesReq.class, paramType = "body")
    public SingleResult<String> changePassword(@NotNull @Validated @RequestBody UserChangePassAesReq passReq) {
        String password = passReq.getA();
        String confirmpassword1 = passReq.getB();
        String passphrase = passReq.getC();
        String iv = passReq.getD();
        String salt = passReq.getE();
        Integer keysize = passReq.getF();
        Integer iterationCount = passReq.getG();
        String confirmpassword2 = passReq.getH();
        Long id = passReq.getI();

        Assert.isTrue(StringUtils.hasText(password)
                        && StringUtils.hasText(confirmpassword1)
                        && StringUtils.hasText(confirmpassword2)
                        && StringUtils.hasText(passphrase)
                        && StringUtils.hasText(iv)
                        && StringUtils.hasText(salt)
                        && iterationCount > 0
                        && keysize > 0
                        && id > 0
                , "参数异常!");

        AesUtil aesUtil = new AesUtil(keysize, iterationCount);
        userService.changePassword(id,
                aesUtil.decrypt(salt, iv, passphrase, password),
                aesUtil.decrypt(salt, iv, passphrase, confirmpassword1),
                aesUtil.decrypt(salt, iv, passphrase, confirmpassword2));
        return ResultUtils.success("Success");
    }

    @ApiOperation("修改用户帐号密码")
    @PostMapping("/changePassword/real")
    @ApiImplicitParam(name = "passReq", value = "修改密码请求参数",
            required = true, dataTypeClass = UserChangePassReq.class, paramType = "body")
    public SingleResult<String> changePassword2(@NotNull @Validated @RequestBody UserChangePassReq passReq) {
        userService.changePassword(passReq.getId(), passReq.getPassword(), passReq.getConfirmPassword1(), passReq.getConfirmPassword2());
        return ResultUtils.success("Success");
    }

    @ApiOperation("根据用户ID查询用户权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户ID,取值于User.id",
                    required = true, dataTypeClass = String.class, paramType = "path"),
            @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, value = "机构序号",
                    required = true, dataTypeClass = Long.class, paramType = "query")
    })
    @GetMapping(value = "/permissions" + PathSuffixConstant.USER_ID)
    public MultResult<UserPermissionRespDto> permissions(@PathVariable(FieldConstant.USER_ID) Long userId,
                                                         @RequestParam(FieldConstant.ORGANIZ_ID) Long organizId) {
        return ResultUtils.success(userPermissionService.findByUserIdAndOrganizId(userId, organizId));
    }

    @ApiOperation("根据用户ID更新用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户权限集合(List<UserPermission>)",
                    required = true, dataTypeClass = List.class, paramType = "body"),
            @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户ID,取值于User.id",
                    required = true, dataTypeClass = Long.class, paramType = "path"),
    })
    @PutMapping(value = "/permissions" + PathSuffixConstant.USER_ID)
    public MultResult<UserPermissionRespDto> permissions(@RequestBody List<UserPermissionReqDto> entities,
                                                         @PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(userPermissionService.processInBatch(userId, entities, false));
    }

    @ApiOperation(value = "根据用户ID重置用户密码", notes = "重置后的密码或是固定密码或是随机密码，具体由机构参数UserDefaultPasswordStrategy决定")
    @ApiImplicitParam(name = IdProp.ID_NAME, value = "用户ID,取值于User.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping("/resetPassword" + PathConstant.PATH_ID)
    public SingleResult<String> resetPassword(@PathVariable(IdProp.ID_NAME) Long id) {
        return ResultUtils.success(userService.resetPassword(id));
    }

    @ApiOperation("根据用户序号查找用户所有角色信息列表")
    @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户ID,取值于User.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = "/roles" + PathSuffixConstant.USER_ID)
    public MultResult<RoleRespDTO> getUserRoles(@PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(roleService.listRoleUsersByRoleId(userId));
    }

    @ApiOperation("更新用户拥有的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entities", value = "用户角色集合(List<UserRole>)",
                    required = true, dataTypeClass = List.class, paramType = "path"),
            @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户ID,取值于User.id",
                    required = true, dataTypeClass = Long.class, paramType = "path")
    })
    @PutMapping(value = "/roles" + PathSuffixConstant.USER_ID)
    public MultResult<UserRoleRespDTO> putUserRoles
            (@RequestBody List<UserRoleReqDto> entities,
             @PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(userRoleService.processInBatch(userId, entities));
    }

    @ApiOperation("根据用户序号查找用户所有角色信息")
    @ApiImplicitParam(name = FieldConstant.USER_ID, value = "用户ID,对应Role.id",
            required = true, dataTypeClass = Integer.class, paramType = "path")
    @GetMapping(value = "/otherRoles" + PathSuffixConstant.USER_ID)
    public MultResult<RoleRespDTO> getOtherRoles(@PathVariable(FieldConstant.USER_ID) Long userId) {
        return ResultUtils.success(roleService.listOtherUsersByRoleId(userId));
    }

    @GetMapping({"/list/organizId/{organizId}/{status}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有用户")
    @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, value = "机构序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public MultResult<UserRespDTO> listByOrganizId(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId,
                                                   @PathVariable("status") Integer status) {
        return ResultUtils.success(userService.listByOrganizId(organizId, status));
    }

    @GetMapping({"/list/topId/{topId}/{status}"})
    @ApiOperation("根据顶级机构ID查询其下所有用户")
    @ApiImplicitParam(name = FieldConstant.TOP_ID, value = "顶级机构ID，传0表示默认查询当前用户的顶级机构序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public MultResult<UserRespDTO> listAllChildByTopId(@PathVariable(FieldConstant.TOP_ID) Long topId,
                                                       @PathVariable("status") Integer status) {
        return ResultUtils.success(userService.listAllChildByTopId(topId, status));
    }

    @Override
    public UserService getService() {
        return userService;
    }
}
