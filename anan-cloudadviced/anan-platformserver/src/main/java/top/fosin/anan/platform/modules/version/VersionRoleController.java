package top.fosin.anan.platform.modules.version;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.platform.modules.version.dto.VersionRoleCreateDTO;
import top.fosin.anan.platform.modules.version.dto.VersionRoleUpdateDTO;
import top.fosin.anan.platform.modules.version.query.VersionRoleQuery;
import top.fosin.anan.platform.modules.version.service.inter.VersionRoleService;
import top.fosin.anan.platform.modules.version.vo.VersionRoleListVO;
import top.fosin.anan.platform.modules.version.vo.VersionRolePageVO;
import top.fosin.anan.platform.modules.version.vo.VersionRoleVO;


/**
 * 系统版本角色表(anan_version_role)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.VERSION_ROLE, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.VERSION_ROLE, tags = "版本角色管理")
public class VersionRoleController
    implements ICreateController<VersionRoleCreateDTO, Long>,
        IRetrieveController<VersionRoleQuery, VersionRoleVO, VersionRoleListVO, VersionRolePageVO, Long>,
        IUpdateController<VersionRoleUpdateDTO, Long>,
        IDeleteController<Long> {

    private final VersionRoleService versionRoleService;

    public VersionRoleController (VersionRoleService versionRoleService) {
        this.versionRoleService = versionRoleService;
    }

    @Override
    public VersionRoleService getService() {
        return versionRoleService;
    }
}
