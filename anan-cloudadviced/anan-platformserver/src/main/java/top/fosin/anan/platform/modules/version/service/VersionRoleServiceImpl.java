package top.fosin.anan.platform.modules.version.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.platform.modules.version.dao.VersionRoleDao;
import top.fosin.anan.platform.modules.version.dto.VersionRoleReqDto;
import top.fosin.anan.platform.modules.version.service.inter.VersionRoleService;

/**
 * 系统版本角色表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
@AllArgsConstructor
public class VersionRoleServiceImpl implements VersionRoleService {
    private final VersionRoleDao versionRoleDao;

    @Override
    public VersionRoleDao getDao() {
        return versionRoleDao;
    }

    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统版本角色表 实体对象
     */
    @Override
    public void preCreate(VersionRoleReqDto entity) {
        VersionRoleService.super.preCreate(entity);
        Assert.isTrue(!entity.getValue().equals(SystemConstant.ANAN_USER_CODE), "角色标识不能为:" + SystemConstant.ANAN_USER_CODE);
    }

    /**
     * 修改数据
     *
     * @param reqDto 系统版本角色表 实体对象
     */
    @Override
    public void preUpdate(VersionRoleReqDto reqDto) {
        VersionRoleService.super.preUpdate(reqDto);
        Assert.isTrue(!reqDto.getValue().equals(SystemConstant.ANAN_USER_CODE), "角色标识不能为:" + SystemConstant.ANAN_USER_CODE);
    }
}
