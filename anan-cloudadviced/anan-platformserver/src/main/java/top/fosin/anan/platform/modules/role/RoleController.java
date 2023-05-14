package top.fosin.anan.platform.modules.role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.RoleUpdateDTO;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.role.dto.RoleCreateDTO;
import top.fosin.anan.platform.modules.role.dto.RoleDTO;
import top.fosin.anan.platform.modules.role.query.RoleQuery;
import top.fosin.anan.platform.modules.role.service.inter.RoleService;
import top.fosin.anan.platform.modules.role.vo.RoleListVO;
import top.fosin.anan.platform.modules.role.vo.RolePageVO;
import top.fosin.anan.platform.modules.role.vo.RoleVO;


/**
 * 系统角色表(anan_role)控制类
 *
 * @author fosin
 * @date 2023-05-14
 */
@RestController
@RequestMapping(value = PathPrefixConstant.ROLE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.ROLE, tags = "角色管理")
public class RoleController
        implements ICreateController<RoleCreateDTO, Long>,
        IRetrieveController<RoleQuery, RoleVO, RoleListVO, RolePageVO, Long>,
        IUpdateController<RoleUpdateDTO, Long>,
        IDeleteController<Long> {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping({"/list/organizId/{organizId}"})
    @ApiOperation("根据机构ID查询该机构及子机构的所有角色")
    @ApiImplicitParam(name = FieldConstant.ORGANIZ_ID, value = "机构序号",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public MultResult<RoleDTO> listByOrganizId(@PathVariable(FieldConstant.ORGANIZ_ID) Long organizId) {
        return ResultUtils.success(roleService.listByOrganizId(organizId));
    }

    @Override
    public RoleService getService() {
        return roleService;
    }
}
