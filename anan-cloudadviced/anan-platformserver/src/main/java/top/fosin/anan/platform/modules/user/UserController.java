package top.fosin.anan.platform.modules.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.UserCreateDTO;
import top.fosin.anan.cloudresource.entity.req.UserUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.core.util.crypt.AesUtil;
import top.fosin.anan.data.constant.PathConstant;
import top.fosin.anan.data.controller.*;
import top.fosin.anan.data.entity.req.PageQuery;
import top.fosin.anan.data.prop.IdProp;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.PageResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.platform.modules.user.dto.UserChangePassAesReq;
import top.fosin.anan.platform.modules.user.dto.UserChangePassReq;
import top.fosin.anan.platform.modules.user.query.UserQuery;
import top.fosin.anan.platform.modules.user.service.inter.UserService;
import top.fosin.anan.platform.modules.user.vo.UserListVO;
import top.fosin.anan.platform.modules.user.vo.UserPageVO;
import top.fosin.anan.platform.modules.user.vo.UserVO;

import jakarta.validation.constraints.NotNull;

/**
 * 用户控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.USER, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "用户管理", description = PathPrefixConstant.USER)
@AllArgsConstructor
public class UserController extends BaseController
        implements ICreateController<UserCreateDTO, Long>,
        IUpdateController<UserUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<UserQuery, UserVO, UserListVO, UserPageVO, Long> {
    private final UserService userService;

    @Override
    @PostMapping(value = PathConstant.PATH_PAGE)
    @Operation(summary = "根据分页条件查询分页数据", description = "根据分页条件查询分页数据")
    @Parameter(name = "pageQuery", description = "分页条件实体类", in = ParameterIn.DEFAULT, required = true)
    public PageResult<UserPageVO> findPage(PageQuery<UserQuery> pageQuery) {
        PageResult<UserPageVO> page = IRetrieveController.super.findPage(pageQuery);
        page.getData().forEach(user -> user.setOrganizName(user.getOrganizId() + ""));
        return page;
    }

    @Operation(summary = "修改用户帐号密码", description = "修改用户帐号密码")
    @PostMapping("/changePassword")
    @Parameter(name = "passReq", description = "修改密码请求参数", in = ParameterIn.DEFAULT, required = true)
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

    @Operation(summary = "修改用户帐号密码", description = "修改用户帐号密码")
    @PostMapping("/changePassword/real")
    @Parameter(name = "passReq", description = "修改密码请求参数", in = ParameterIn.DEFAULT, required = true)
    public SingleResult<String> changePassword2(@NotNull @Validated @RequestBody UserChangePassReq passReq) {
        userService.changePassword(passReq.getId(), passReq.getPassword(), passReq.getConfirmPassword1(), passReq.getConfirmPassword2());
        return ResultUtils.success("Success");
    }


    @Operation(summary = "根据用户ID重置用户密码", description = "重置后的密码或是固定密码或是随机密码，具体由机构参数UserDefaultPasswordStrategy决定")
    @Parameter(name = IdProp.ID_NAME, description = "用户ID,取值于User.id", in = ParameterIn.DEFAULT, required = true)
    @GetMapping("/resetPassword" + PathConstant.PATH_ID)
    public SingleResult<String> resetPassword(@PathVariable(IdProp.ID_NAME) Long id) {
        return ResultUtils.success(userService.resetPassword(id));
    }

    @GetMapping({"/list/organizId/{organizId}/{status}"})
    @Operation(summary = "根据机构ID查询该机构及子机构的所有用户", description = "根据机构ID查询该机构及子机构的所有用户")
    @Parameter(name = FieldConstant.ORGANIZ_ID, description = "机构序号", in = ParameterIn.DEFAULT, required = true)
    public MultResult<UserDTO> listByOrganizId(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId,
                                               @PathVariable("status") Byte status) {
        return ResultUtils.success(userService.listByOrganizId(organizId, status));
    }

    @GetMapping({"/list/topId/{topId}/{status}"})
    @Operation(summary = "根据顶级机构ID查询其下所有用户", description = "根据顶级机构ID查询其下所有用户")
    @Parameter(name = FieldConstant.TOP_ID, description = "顶级机构ID，传0表示默认查询当前用户的顶级机构序号", in = ParameterIn.DEFAULT, required = true)
    public MultResult<UserDTO> listAllChildByTopId(@PathVariable(FieldConstant.TOP_ID) Long topId,
                                                   @PathVariable("status") Byte status) {
        return ResultUtils.success(userService.listAllChildByTopId(topId, status));
    }

    @Override
    public UserService getService() {
        return userService;
    }
}
