package top.fosin.anan.platform.modules.organization.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.platform.modules.organization.dao.OrgPermissionDao;
import top.fosin.anan.platform.modules.organization.service.inter.OrgPermissionService;

/**
 * 系统机构权限系统机构权限表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
@AllArgsConstructor
public class OrgPermissionServiceImpl implements OrgPermissionService {
    private final OrgPermissionDao orgPermissionDao;

    /**
     * 获取DAO
     */
    @Override
    public OrgPermissionDao getDao() {
        return orgPermissionDao;
    }
}
