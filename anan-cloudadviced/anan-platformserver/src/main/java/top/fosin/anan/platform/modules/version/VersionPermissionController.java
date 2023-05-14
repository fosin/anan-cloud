package top.fosin.anan.platform.modules.version;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.data.controller.batch.IUpdateBatchController;
import top.fosin.anan.platform.modules.version.dto.VersionPermissionUpdateDTO;
import top.fosin.anan.platform.modules.version.service.inter.VersionPermissionService;
import top.fosin.anan.platform.modules.version.vo.VersionPermissionVO;


/**
 * 系统版本权限表(anan_version_permission)控制类
 *
 * @author fosin
 * @date 2023-05-13
 */
@RestController
@RequestMapping(value = PathPrefixConstant.VERSION_PERMISSION, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.VERSION_PERMISSION, tags = "版本权限管理")
public class VersionPermissionController implements
        IUpdateBatchController<VersionPermissionUpdateDTO, Long>,
        IRetrieveBatchController<VersionPermissionVO, Long> {

    private final VersionPermissionService versionPermissionService;

    public VersionPermissionController(VersionPermissionService versionPermissionService) {
        this.versionPermissionService = versionPermissionService;
    }

    @Override
    public VersionPermissionService getBatchService() {
        return versionPermissionService;
    }
}
