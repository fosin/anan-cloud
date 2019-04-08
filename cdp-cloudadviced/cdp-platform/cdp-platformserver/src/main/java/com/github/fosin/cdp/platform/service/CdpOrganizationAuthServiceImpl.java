package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.dto.request.CdpOrganizationAuthCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayOrderCreateDto;
import com.github.fosin.cdp.platform.entity.CdpOrganizationAuthEntity;
import com.github.fosin.cdp.platform.entity.CdpPayOrderEntity;
import com.github.fosin.cdp.platform.entity.CdpVersionEntity;
import com.github.fosin.cdp.platform.repository.CdpOrganizationAuthRepository;
import com.github.fosin.cdp.platform.service.inter.CdpOrganizationAuthService;
import com.github.fosin.cdp.platform.service.inter.CdpPayOrderService;
import com.github.fosin.cdp.platform.service.inter.CdpVersionService;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationUpdateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserRegisterDto;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;
import com.github.fosin.cdp.platform.service.inter.OrganizationService;
import com.github.fosin.cdp.platform.service.inter.UserService;
import com.github.fosin.cdp.util.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统机构授权表(cdp_organization_auth)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpOrganizationAuthServiceImpl implements CdpOrganizationAuthService {
    @Autowired
    private CdpOrganizationAuthRepository cdpSysOrganizationAuthRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpOrganizationAuthRepository getRepository() {
        return cdpSysOrganizationAuthRepository;
    }

    @Override
    public List<CdpOrganizationAuthEntity> findAllByVersionId(Long versionId) {
        return getRepository().findAllByVersionId(versionId);
    }

    @Override
    public List<CdpOrganizationAuthEntity> findAllByOrganizId(Long organizId) {
        return getRepository().findAllByOrganizId(organizId);
    }

    @Autowired
    private CdpOrganizationAuthService organizationAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private CdpPayOrderService payOrderService;

    @Autowired
    private CdpVersionService versionService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Boolean register(RegisterDto registerDto) {
        Date now = new Date();

        //创建机构
        CdpOrganizationCreateDto organization = new CdpOrganizationCreateDto();
        BeanUtils.copyProperties(registerDto.getOrganization(), organization);
        organization.setPId(0L);
        organization.setTopId(0L);
        organization.setStatus(0);
        CdpOrganizationEntity organizationEntity = organizationService.create(organization);
        CdpOrganizationUpdateDto updateDto = new CdpOrganizationUpdateDto();
        BeanUtils.copyProperties(organizationEntity, updateDto);
        updateDto.setTopId(organizationEntity.getId());
        organizationService.update(updateDto);

        //创建用户
        CdpUserRegisterDto registerDTO = registerDto.getUser();
        CdpUserCreateDto createDTO = new CdpUserCreateDto();
        BeanUtils.copyProperties(registerDTO, createDTO);
        createDTO.setOrganizId(updateDto.getId());

        try {
            createDTO.setExpireTime(new SimpleDateFormat(DateTimeUtil.DATETIME_PATTERN).parse("2050-12-31 23:59:59"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Assert.isTrue(createDTO.getPassword().equals(createDTO.getConfirmPassword()), "密码和确认密码必须一致!");
        CdpUserEntity user = userService.create(createDTO);


        Long versionId = registerDto.getVersionId();
        CdpVersionEntity versionEntity = versionService.findById(versionId);

        //创建订单
        CdpPayOrderCreateDto orderEntity = new CdpPayOrderCreateDto();
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
        CdpPayOrderEntity orderSaveEntity = payOrderService.create(orderEntity);

        //创建机构认证信息
        CdpOrganizationAuthCreateDto auth = new CdpOrganizationAuthCreateDto();
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

    private String getAuthCode(CdpOrganizationAuthCreateDto auth) {
        return passwordEncoder.encode(auth.toString());
    }
}
