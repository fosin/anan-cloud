package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.platform.dto.request.AnanOrganizationAuthCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayOrderCreateDto;
import com.github.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import com.github.fosin.anan.platform.entity.AnanPayOrderEntity;
import com.github.fosin.anan.platform.entity.AnanVersionEntity;
import com.github.fosin.anan.platform.repository.AnanOrganizationAuthRepository;
import com.github.fosin.anan.platform.service.inter.AnanOrganizationAuthService;
import com.github.fosin.anan.platform.service.inter.AnanPayOrderService;
import com.github.fosin.anan.platform.service.inter.AnanVersionService;
import com.github.fosin.anan.pojo.dto.RegisterDto;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanOrganizationUpdateDto;
import com.github.fosin.anan.pojo.dto.request.AnanUserCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanUserRegisterDto;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platform.service.inter.OrganizationService;
import com.github.fosin.anan.platform.service.inter.UserService;
import com.github.fosin.anan.util.DateTimeUtil;
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
 * 系统机构授权表(anan_organization_auth)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanOrganizationAuthServiceImpl implements AnanOrganizationAuthService {
    private final AnanOrganizationAuthRepository ananSysOrganizationAuthRepository;

    private final UserService userService;

    private final AnanPayOrderService payOrderService;

    private final AnanVersionService versionService;
    private final OrganizationService organizationService;

    private final PasswordEncoder passwordEncoder;

    public AnanOrganizationAuthServiceImpl(AnanOrganizationAuthRepository ananSysOrganizationAuthRepository, UserService userService, AnanPayOrderService payOrderService, AnanVersionService versionService, OrganizationService organizationService, PasswordEncoder passwordEncoder) {
        this.ananSysOrganizationAuthRepository = ananSysOrganizationAuthRepository;
        this.userService = userService;
        this.payOrderService = payOrderService;
        this.versionService = versionService;
        this.organizationService = organizationService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanOrganizationAuthRepository getRepository() {
        return ananSysOrganizationAuthRepository;
    }

    @Override
    public List<AnanOrganizationAuthEntity> findAllByVersionId(Long versionId) {
        return getRepository().findAllByVersionId(versionId);
    }

    @Override
    public List<AnanOrganizationAuthEntity> findAllByOrganizId(Long organizId) {
        return getRepository().findAllByOrganizId(organizId);
    }

    @Override
    @Transactional
    public Boolean register(RegisterDto registerDto) {
        Date now = new Date();

        //创建机构
        AnanOrganizationCreateDto organization = new AnanOrganizationCreateDto();
        BeanUtils.copyProperties(registerDto.getOrganization(), organization);
        organization.setPid(0L);
        organization.setTopId(0L);
        organization.setStatus(0);
        AnanOrganizationEntity organizationEntity = organizationService.create(organization);
        AnanOrganizationUpdateDto updateDto = new AnanOrganizationUpdateDto();
        BeanUtils.copyProperties(organizationEntity, updateDto);
        updateDto.setTopId(organizationEntity.getId());
        organizationService.update(updateDto);

        //创建用户
        AnanUserRegisterDto registerDTO = registerDto.getUser();
        AnanUserCreateDto createDTO = new AnanUserCreateDto();
        BeanUtils.copyProperties(registerDTO, createDTO);
        createDTO.setOrganizId(updateDto.getId());

        try {
            createDTO.setExpireTime(new SimpleDateFormat(DateTimeUtil.DATETIME_PATTERN).parse("2050-12-31 23:59:59"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Assert.isTrue(createDTO.getPassword().equals(createDTO.getConfirmPassword()), "密码和确认密码必须一致!");
        AnanUserEntity user = userService.create(createDTO);


        Long versionId = registerDto.getVersionId();
        AnanVersionEntity versionEntity = versionService.findById(versionId);

        //创建订单
        AnanPayOrderCreateDto orderEntity = new AnanPayOrderCreateDto();
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
        AnanPayOrderEntity orderSaveEntity = payOrderService.create(orderEntity);

        //创建机构认证信息
        AnanOrganizationAuthCreateDto auth = new AnanOrganizationAuthCreateDto();
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
        this.create(auth);

        return true;
    }

    private String getAuthCode(AnanOrganizationAuthCreateDto auth) {
        return passwordEncoder.encode(auth.toString());
    }
}
