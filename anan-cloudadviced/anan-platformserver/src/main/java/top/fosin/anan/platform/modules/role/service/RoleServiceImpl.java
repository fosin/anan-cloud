package top.fosin.anan.platform.modules.role.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.entity.req.PageQuery;
import top.fosin.anan.data.result.PageResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.platform.modules.organization.dao.OrgDao;
import top.fosin.anan.platform.modules.organization.po.Organization;
import top.fosin.anan.platform.modules.role.dao.RoleDao;
import top.fosin.anan.platform.modules.role.dto.RoleCreateDTO;
import top.fosin.anan.platform.modules.role.dto.RoleDTO;
import top.fosin.anan.cloudresource.entity.req.RoleUpdateDTO;
import top.fosin.anan.platform.modules.role.po.Role;
import top.fosin.anan.platform.modules.role.query.RoleQuery;
import top.fosin.anan.platform.modules.role.service.inter.RoleService;
import top.fosin.anan.platform.modules.user.dao.UserRoleDao;
import top.fosin.anan.platform.modules.user.po.UserRole;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    private final UserRoleDao userRoleDao;
    private final OrgDao orgDao;
    private final CurrentUserService currentUserService;

    @Override
    public RoleDTO create(RoleCreateDTO dto) {
        String value = dto.getValue();
        if (SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(value) &&
                !SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("不能创建角色标识" + SystemConstant.ADMIN_ROLE_NAME + "!");
        }
        Assert.isTrue(!SystemConstant.ANAN_ROLE_NAME.equalsIgnoreCase(value),
                "不能创建超级管理员角色帐号信息!");
        Role entityDynamic = poByEntity(dto);
        Assert.isNull(entityDynamic, "该角色已存在，请核对!");
        Role saveEntity = BeanUtil.copyProperties(dto, Role.class);
        Role save = roleDao.save(saveEntity);
        return BeanUtil.copyProperties(save, RoleDTO.class);
    }

    @Override
    public void update(RoleUpdateDTO reqDto, String[] ignoreProperties) {
        Assert.notNull(reqDto, "传入了空对象!");
        Long id = reqDto.getId();
        Assert.notNull(id, "传入了空ID!");

        Role oldEntity = roleDao.findById(id).orElse(null);
        Assert.notNull(oldEntity, "通过传入的ID：" + id + "未能找到数据!");
        if (SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(oldEntity.getValue()) &&
                !SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(reqDto.getValue())) {
            throw new IllegalArgumentException("不能修改角色标识" + SystemConstant.ADMIN_ROLE_NAME + "!");
        }
        Assert.isTrue(!SystemConstant.ANAN_ROLE_NAME.equalsIgnoreCase(oldEntity.getValue()),
                "不能修改超级管理员角色帐号信息!");
        Role saveEntity = BeanUtil.copyProperties(reqDto, Role.class, ignoreProperties);
        roleDao.save(saveEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的角色ID无效！");
        Role entity = roleDao.findById(id).orElse(null);
        Assert.notNull(entity, "根据角色ID未能找到角色数据!");
        deleteByEntity(entity);
    }

    private void deleteByEntity(Role entity) {
        Assert.isTrue(!SystemConstant.ANAN_ROLE_NAME.equals(entity.getValue())
                        && !SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue()),
                "不能删除(超级)管理员角色帐号信息!");

        List<UserRole> roleUsers = userRoleDao.findByRoleId(entity.getId());
        Assert.isTrue(roleUsers.size() == 0,
                "该角色下还存在用户,不能直接删除角色!");
        roleDao.delete(entity);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<Role> entities = roleDao.findAllById(ids);
        for (Role entity : entities) {
            deleteByEntity(entity);
        }
    }

    @Override
    public List<RoleDTO> listOtherUsersByRoleId(Long userId) {
        return BeanUtil.copyProperties(roleDao.findOtherRolesByUserId(userId), RoleDTO.class);
    }

    @Override
    public List<RoleDTO> listRoleUsersByRoleId(Long userId) {
        return BeanUtil.copyProperties(roleDao.findUserRolesByUserId(userId), RoleDTO.class);
    }

    @Override
    public PageResult<RoleDTO> findPage(PageQuery<?> PageQuery) {
        Assert.notNull(PageQuery, "传入的分页信息不能为空!");
        RoleQuery params = (RoleQuery) PageQuery.getParams();

        Specification<Role> condition;
        if (currentUserService.hasSysAdminRole()) {
            //超过管理员不需要通过机构序号间接查询
            params.setOrganizId(null);
            condition = buildQueryRules(params, false);
        } else {
            Assert.notNull(params, "传入的分页信息不能为空!");
            Long organizId = params.getOrganizId();
            if (organizId == null || organizId < 1) {
                organizId = currentUserService.getOrganizId();
            }
            String name = params.getName();
            String value = params.getValue();
            Assert.notNull(organizId, "机构ID不能为空!");
            Organization organiz = orgDao.findById(organizId).orElseThrow(() -> new IllegalArgumentException("根据传入的机构编码没有找到任何数据!"));
            List<Organization> organizs = orgDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organiz.getTopId(),organiz.getCode());

            condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> roleName = root.get("name");
                Path<String> roleValue = root.get("value");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (Organization entity : organizs) {
                    in.value(entity.getId());
                }

                Predicate predicate = cb.and(in, cb.notEqual(roleValue, SystemConstant.ANAN_ROLE_NAME));
                if (StringUtils.hasText(name) || StringUtils.hasText(value)) {
                    predicate = cb.and(predicate, cb.or(cb.like(roleName, "%" + name + "%"), cb.like(roleValue, "%" + value + "%")));
                }
                return predicate;
            };
        }
        PageRequest pageable = toPage(PageQuery);
        Page<Role> page = roleDao.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(),
                BeanUtil.copyProperties(page.getContent(), RoleDTO.class));
    }

    @Override
    public List<RoleDTO> listByOrganizId(Long organizId) {
        Assert.notNull(organizId, "机构ID不能为空!");
        List<Role> entities;
        if (currentUserService.hasSysAdminRole()) {
            entities = roleDao.findAll();
        } else {
            Organization organiz = orgDao.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            Organization topOrganiz = orgDao.findById(organiz.getTopId()).orElse(null);
            List<Organization> organizs = orgDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organiz.getTopId(), Objects.requireNonNull(topOrganiz).getCode());

            Specification<Role> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> roleValue = root.get("value");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (Organization entity : organizs) {
                    in.value(entity.getId());
                }
                return cb.and(in, cb.notEqual(roleValue, SystemConstant.ANAN_ROLE_NAME));
            };
            entities = roleDao.findAll(condition);
        }
        return BeanUtil.copyProperties(entities, RoleDTO.class);
    }

    @Override
    public RoleDao getDao() {
        return roleDao;
    }
}
