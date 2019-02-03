package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.dto.request.CdpSysOrganizationAuthCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysPayOrderCreateDto;
import com.github.fosin.cdp.platform.entity.CdpSysOrganizationAuthEntity;
import com.github.fosin.cdp.platform.entity.CdpSysPayOrderEntity;
import com.github.fosin.cdp.platform.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.platform.repository.CdpSysOrganizationAuthRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationAuthService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysPayOrderService;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionService;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysOrganizationCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysOrganizationUpdateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserRegisterDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.service.inter.IOrganizationService;
import com.github.fosin.cdp.platformapi.service.inter.IUserService;
import com.github.fosin.cdp.util.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        //创建机构
        CdpSysOrganizationCreateDto organization = new CdpSysOrganizationCreateDto();
        BeanUtils.copyProperties(registerDto.getOrganization(), organization);
        organization.setPId(0L);
        organization.setTopId(0L);
        organization.setStatus(0);
        CdpSysOrganizationEntity organizationEntity = organizationService.create(organization);
        CdpSysOrganizationUpdateDto updateDto = new CdpSysOrganizationUpdateDto();
        BeanUtils.copyProperties(organizationEntity, updateDto);
        updateDto.setTopId(organizationEntity.getId());
        organizationService.update(updateDto);

        //创建用户
        CdpSysUserRegisterDto registerDTO = registerDto.getUser();
        CdpSysUserCreateDto createDTO = new CdpSysUserCreateDto();
        BeanUtils.copyProperties(registerDTO, createDTO);
        createDTO.setOrganizId(updateDto.getId());

        try {
            createDTO.setExpireTime(new SimpleDateFormat(DateTimeUtil.DATETIME_PATTERN).parse("2050-12-31 23:59:59"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Assert.isTrue(createDTO.getPassword().equals(createDTO.getConfirmPassword()), "密码和确认密码必须一致!");
        CdpSysUserEntity user = userService.create(createDTO);


        Long versionId = registerDto.getVersionId();
        CdpSysVersionEntity versionEntity = versionService.findOne(versionId);

        //创建订单
        CdpSysPayOrderCreateDto orderEntity = new CdpSysPayOrderCreateDto();
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
        CdpSysPayOrderEntity orderSaveEntity = payOrderService.create(orderEntity);

        //创建机构认证信息
        CdpSysOrganizationAuthCreateDto auth = new CdpSysOrganizationAuthCreateDto();
        auth.setVersionId(versionId);
        auth.setOrderId(orderSaveEntity.getId());
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

    private String getAuthCode(CdpSysOrganizationAuthCreateDto auth) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(auth.toString());
    }
}