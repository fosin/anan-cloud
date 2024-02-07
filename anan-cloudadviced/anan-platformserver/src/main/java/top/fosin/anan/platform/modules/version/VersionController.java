package top.fosin.anan.platform.modules.version;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.res.PermissionTreeDTO;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.data.entity.res.TreeVO;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.permission.service.inter.PermissionService;
import top.fosin.anan.platform.modules.version.dto.VersionCreateDTO;
import top.fosin.anan.platform.modules.version.dto.VersionUpdateDTO;
import top.fosin.anan.platform.modules.version.query.VersionQuery;
import top.fosin.anan.platform.modules.version.service.inter.VersionService;
import top.fosin.anan.platform.modules.version.vo.VersionListVO;
import top.fosin.anan.platform.modules.version.vo.VersionPageVO;
import top.fosin.anan.platform.modules.version.vo.VersionVO;


/**
 * 系统版本表(anan_version)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.VERSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Tag(name = "版本管理", description = PathPrefixConstant.VERSION)
public class VersionController
    implements ICreateController<VersionCreateDTO, Long>,
        IRetrieveController<VersionQuery, VersionVO, VersionListVO, VersionPageVO, Long>,
        IUpdateController<VersionUpdateDTO, Long>,
        IDeleteController<Long> {
    private final VersionService versionService;
    private final PermissionService permissionService;

    public VersionController (VersionService versionService, PermissionService permissionService) {
        this.versionService = versionService;
        this.permissionService = permissionService;
    }

    @Operation(summary = "根据父权限ID获取其孩子数据列表", description = "根据父权限ID获取其孩子数据列表")
    @GetMapping(value = "/listChild/{pid}")
    @Parameters({
            @Parameter(name = "versionId", required = true),
            @Parameter(name = TreeVO.PID_NAME, required = true)
    })
    public MultResult<PermissionTreeDTO> getListChild(@PathVariable Long pid, @RequestParam Long versionId) {
        return ResultUtils.success(permissionService.findByPidAndVersionId(pid, versionId));
    }

    @Override
    public VersionService getService() {
        return versionService;
    }
}
