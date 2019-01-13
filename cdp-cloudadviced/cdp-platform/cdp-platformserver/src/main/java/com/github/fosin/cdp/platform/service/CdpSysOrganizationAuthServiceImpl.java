package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.platform.entity.CdpSysPayOrderEntity;
import com.github.fosin.cdp.platform.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.platform.repository.CdpSysOrganizationAuthRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationAuthService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysPayOrderService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionService;
import com.github.fosin.cdp.platformapi.dto.CdpSysUserRequestDto;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.service.inter.IOrganizationService;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 系统机构授权表(cdp_sys_organization_auth)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysOrganizationAuthServiceImpl implements ICdpSysOrganizationAuthService {
    @Autowired
    private CdpSysOrganizationAuthRepository cdpSysOrganizationAuthRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysOrganizationAuthRepository getRepository() {
        return cdpSysOrganizationAuthRepository;
    }

    /**
     * 通过实体类创建新数据
     *
     * @param entity 系统机构授权表 实体对象
     * @return entity 实例对象
     */
    @Override
    public CdpSysOrganizationAuthEntity create(CdpSysOrganizationAuthEntity entity) {
        Assert.notNull(entity, "创建数据的实体对象不能为空!");
        entity.setCreateBy(1L);
        entity.setCreateTime(new Date());
        return getRepository().save(entity);
    }

    @Override
    public List<CdpSysOrganizationAuthEntity> findAllByVersionId(Long versionId) {
        return getRepository().findAllByVersionId(versionId);
    }

    @Override
    public List<CdpSysOrganizationAuthEntity> findAllByOrganizId(Long organizId) {
        return getRepository().findAllByOrganizId(organizId);
    }

    @Autowired
    private ICdpSysOrganizationAuthService organizationAuthService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICdpSysPayOrderService payOrderService;

    @Autowired
    private ICdpSysVersionService versionService;
    @Autowired
    private IOrganizationService organizationService;

    @Override
    @Transactional
    public Boolean register(RegisterDto registerDto) {
        Date now = new Date();

        //创建用户
        CdpSysUserRequestDto.CreateDto createDTO = registerDto.getUser();
//        Assert.isTrue(createDTO.getPassword().equals(createDTO.getConfirmPassword()), "密码和确认密码必须一致!");
        CdpSysUserEntity user = userService.create(createDTO);

        //创建机构
        CdpSysOrganizationEntity organization = new CdpSysOrganizationEntity();
        BeanUtils.copyProperties(registerDto.getOrganization(), organization);
        organization.setPId(0L);
        organization.setTopId(0L);
        organization.setLevel(1);
        organization.setStatus(0);
        CdpSysOrganizationEntity organizationEntity = organizationService.create(organization);
        organizationEntity.setTopId(organizationEntity.getId());
        organizationService.update(organizationEntity);

        Long versionId = registerDto.getVersionId();
        CdpSysVersionEntity versionEntity = versionService.findOne(versionId);

        //创建订单
        CdpSysPayOrderEntity orderEntity = new CdpSysPayOrderEntity();
        orderEntity.setMoney(versionEntity.getPrice());
        orderEntity.setOrderTime(now);
        orderEntity.setVersionId(versionId);
        orderEntity.setOrganizId(organizationEntity.getId());
        orderEntity.setUserId(user.getId());
        if (versionEntity.getPrice() == 0) {
            orderEntity.setStatus(1);
            orderEntity.setPayTime(now);
        } else {
            orderEntity.setStatus(0);
        }
        orderEntity = payOrderService.create(orderEntity);


        //创建机构认证信息
        CdpSysOrganizationAuthEntity auth = new CdpSysOrganizationAuthEntity();
        auth.setVersionId(versionId);
        auth.setOrderId(orderEntity.getOrderId());
        auth.setMaxOrganizs(versionEntity.getMaxOrganizs());
        auth.setMaxUsers(versionEntity.getMaxUsers());
        auth.setOrganizId(organizationEntity.getId());
        auth.setProtectDays(versionEntity.getProtectDays());
        auth.setTryout(versionEntity.getTryout());
        auth.setTryoutDays(versionEntity.getTryoutDays());
        auth.setValidity(versionEntity.getValidity());
        auth.setAuthorizationCode(getAuthCode(auth));
        organizationAuthService.create(auth);

        return true;
    }

    private String getAuthCode(CdpSysOrganizationAuthEntity auth) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(auth.toString());
    }
}