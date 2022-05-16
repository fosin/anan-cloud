package top.fosin.anan.platform.modules.version.service;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.version.dto.VersionRoleReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRoleRespDto;
import top.fosin.anan.platform.modules.version.entity.VersionRole;
import top.fosin.anan.platform.modules.version.dao.VersionRoleDao;
import top.fosin.anan.platform.modules.version.service.inter.VersionRoleService;

/**
 * 系统版本角色表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class VersionRoleServiceImpl implements VersionRoleService {
    private final VersionRoleDao ananSysVersionRoleDao;

    public VersionRoleServiceImpl(VersionRoleDao ananSysVersionRoleDao) {
        this.ananSysVersionRoleDao = ananSysVersionRoleDao;
    }

    /**
     * 获取DAO
     */
    @Override
    public VersionRoleDao getRepository() {
        return ananSysVersionRoleDao;
    }

    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统版本角色表 实体对象
     * @return entity 实例对象
     */
    @Override
    public VersionRoleRespDto create(VersionRoleReqDto entity) {
        Assert.notNull(entity, "创建数据的实体对象不能为空!");
        Assert.isTrue(!entity.getValue().equals(SystemConstant.ANAN_USER_CODE), "角色标识不能为:" + SystemConstant.ANAN_USER_CODE);
        VersionRole createEntity = new VersionRole();
        BeanUtils.copyProperties(entity, createEntity);
        return BeanUtil.copyProperties(getRepository().save(createEntity), VersionRoleRespDto.class);
    }

    /**
     * 修改数据
     *
     * @param entity 系统版本角色表 实体对象
     * @return entity 实例对象
     */
    @Override
    public void update(VersionRoleReqDto entity) {
        Assert.notNull(entity, "更新数据的实体对象不能为空!");
        Long id = entity.getId();
        Assert.isTrue(id != null && id > 0, "传入的主键无效!");
        Assert.isTrue(!entity.getValue().equals(SystemConstant.ANAN_USER_CODE), "角色标识不能为:" + SystemConstant.ANAN_USER_CODE);
        VersionRole createEntity = getRepository().findById(id).orElse(null);
        Assert.notNull(createEntity, "更新数据的实体对象不能为空!");
        BeanUtils.copyProperties(entity, createEntity);
        getRepository().save(createEntity);
    }
}
