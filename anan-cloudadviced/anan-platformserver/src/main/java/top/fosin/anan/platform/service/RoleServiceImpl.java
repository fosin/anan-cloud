package top.fosin.anan.platform.service;


import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.req.AnanRoleReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanRoleRespDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.ListResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;
import top.fosin.anan.platform.entity.AnanRoleEntity;
import top.fosin.anan.platform.entity.AnanUserRoleEntity;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.repository.RoleRepository;
import top.fosin.anan.platform.repository.UserRoleRepository;
import top.fosin.anan.platform.service.inter.RoleService;

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
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final OrganizationRepository organizationRepository;
    private final AnanUserDetailService ananUserDetailService;

    public RoleServiceImpl(RoleRepository roleRepository, UserRoleRepository userRoleRepository, OrganizationRepository organizationRepository, AnanUserDetailService ananUserDetailService) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.organizationRepository = organizationRepository;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AnanRoleRespDto create(AnanRoleReqDto dto) {
        String value = dto.getValue();
        if (SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(value) &&
                !SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("不能创建角色标识" + SystemConstant.ADMIN_ROLE_NAME + "!");
        }
        Assert.isTrue(!SystemConstant.ANAN_ROLE_NAME.equalsIgnoreCase(value),
                "不能创建超级管理员角色帐号信息!");
        AnanRoleEntity entityDynamic = this.queryOneByDto(dto);
        Assert.isNull(entityDynamic, "该角色已存在，请核对!");
        AnanRoleEntity saveEntity = new AnanRoleEntity();
        BeanUtils.copyProperties(dto, saveEntity);
        AnanRoleEntity save = roleRepository.save(saveEntity);
        AnanRoleRespDto respDto = new AnanRoleRespDto();
        BeanUtils.copyProperties(save, respDto);
        return respDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AnanRoleReqDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");

        AnanRoleEntity oldEntity = roleRepository.findById(id).orElse(null);
        Assert.notNull(oldEntity, "通过传入的ID：" + id + "未能找到数据!");
        if (SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(oldEntity.getValue()) &&
                !SystemConstant.ADMIN_ROLE_NAME.equalsIgnoreCase(entity.getValue())) {
            throw new IllegalArgumentException("不能修改角色标识" + SystemConstant.ADMIN_ROLE_NAME + "!");
        }
        Assert.isTrue(!SystemConstant.ANAN_ROLE_NAME.equalsIgnoreCase(oldEntity.getValue()),
                "不能修改超级管理员角色帐号信息!");
        AnanRoleEntity saveEntity = new AnanRoleEntity();
        BeanUtils.copyProperties(entity, saveEntity);
        roleRepository.save(saveEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        Assert.isTrue(id != null && id > 0, "传入的角色ID无效！");
        AnanRoleEntity entity = roleRepository.findById(id).orElse(null);
        Assert.notNull(entity, "根据角色ID未能找到角色数据!");
        deleteByEntity(entity);
    }

    private void deleteByEntity(AnanRoleEntity entity) {
        Assert.isTrue(!SystemConstant.ANAN_ROLE_NAME.equals(entity.getValue())
                        && !SystemConstant.ADMIN_ROLE_NAME.equals(entity.getValue()),
                "不能删除(超级)管理员角色帐号信息!");

        List<AnanUserRoleEntity> roleUsers = userRoleRepository.findByRoleId(entity.getId());
        Assert.isTrue(roleUsers.size() == 0,
                "该角色下还存在用户,不能直接删除角色!");
        roleRepository.delete(entity);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<AnanRoleEntity> entities = roleRepository.findAllById(ids);
        for (AnanRoleEntity entity : entities) {
            deleteByEntity(entity);
        }
    }

    @Override
    public List<AnanRoleRespDto> findOtherUsersByRoleId(Long userId) {
        return BeanUtil.copyCollectionProperties(roleRepository.findOtherRolesByUserId(userId), AnanRoleRespDto.class);
    }

    @Override
    public List<AnanRoleRespDto> findRoleUsersByRoleId(Long userId) {
        return BeanUtil.copyCollectionProperties(roleRepository.findUserRolesByUserId(userId), AnanRoleRespDto.class);
    }

    @Override
    public ListResult<AnanRoleRespDto> findPage(PageModule<AnanRoleReqDto> pageModule) {
        Assert.notNull(pageModule, "传入的分页信息不能为空!");
        AnanRoleReqDto params = pageModule.getParams();

        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), this.buildSortRules(params.getSortRules()));

        Page<AnanRoleEntity> page;
        Specification<AnanRoleEntity> condition;
        if (ananUserDetailService.hasSysAdminRole()) {
            condition = buildQueryRules(params, false);
        } else {
            Assert.notNull(params, "传入的分页信息不能为空!");
            Long organizId = params.getOrganizId();
            String name = params.getName();
            String value = params.getValue();
            Assert.notNull(organizId, "机构ID不能为空!");
            AnanOrganizationEntity organiz = organizationRepository.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            List<AnanOrganizationEntity> organizs = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(organiz.getTopId(), organiz.getCode());

            condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> roleName = root.get("name");
                Path<String> roleValue = root.get("value");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (AnanOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }

                Predicate predicate = cb.and(in, cb.notEqual(roleValue, SystemConstant.ANAN_ROLE_NAME));
                if (StringUtils.hasText(name) || StringUtils.hasText(value)) {
                    predicate = cb.and(predicate, cb.or(cb.like(roleName, "%" + name + "%"), cb.like(roleValue, "%" + value + "%")));
                }
                return predicate;
            };
        }
        page = roleRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getTotalPages(),
                BeanUtil.copyCollectionProperties(page.getContent(), AnanRoleRespDto.class));
    }

    @Override
    public List<AnanRoleRespDto> findAllByOrganizId(Long organizId) {
        Assert.notNull(organizId, "机构ID不能为空!");
        List<AnanRoleEntity> entities;
        if (ananUserDetailService.hasSysAdminRole()) {
            entities = roleRepository.findAll();
        } else {
            AnanOrganizationEntity organiz = organizationRepository.findById(organizId).orElse(null);
            Assert.notNull(organiz, "根据传入的机构编码没有找到任何数据!");
            AnanOrganizationEntity topOrganiz = organizationRepository.findById(organiz.getTopId()).orElse(null);
            List<AnanOrganizationEntity> organizs = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(organiz.getTopId(), Objects.requireNonNull(topOrganiz).getCode());

            Specification<AnanRoleEntity> condition = (root, query, cb) -> {
                Path<Long> organizIdPath = root.get("organizId");
                Path<String> roleValue = root.get("value");

                CriteriaBuilder.In<Long> in = cb.in(organizIdPath);
                for (AnanOrganizationEntity entity : organizs) {
                    in.value(entity.getId());
                }
                return cb.and(in, cb.notEqual(roleValue, SystemConstant.ANAN_ROLE_NAME));
            };
            entities = roleRepository.findAll(condition);
        }
        return BeanUtil.copyCollectionProperties(entities, AnanRoleRespDto.class);
    }

    @Override
    public RoleRepository getRepository() {
        return roleRepository;
    }
}
