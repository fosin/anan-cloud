package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.dto.req.AnanUserRegisterDto;
import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.platform.dto.req.*;
import top.fosin.anan.platform.dto.res.AnanOrganizationAuthRespDto;
import top.fosin.anan.platform.dto.res.AnanPayOrderRespDto;
import top.fosin.anan.platform.dto.res.AnanVersionRespDto;
import top.fosin.anan.platform.repository.OrganizationAuthRepository;
import top.fosin.anan.platform.service.inter.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统机构授权系统机构授权表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class OrganizationAuthServiceImpl implements OrganizationAuthService {
    private final OrganizationAuthRepository ananSysOrganizationAuthRepository;

    private final UserService userService;

    private final PayOrderService payOrderService;

    private final VersionService versionService;
    private final OrganizationService organizationService;

    private final PasswordEncoder passwordEncoder;

    public OrganizationAuthServiceImpl(OrganizationAuthRepository ananSysOrganizationAuthRepository, UserService userService, PayOrderService payOrderService, VersionService versionService, OrganizationService organizationService, PasswordEncoder passwordEncoder) {
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
    public OrganizationAuthRepository getRepository() {
        return ananSysOrganizationAuthRepository;
    }

    @Override
    public List<AnanOrganizationAuthRespDto> findAllByVersionId(Long versionId) {
        return BeanUtil.copyCollectionProperties(getRepository().findAllByVersionId(versionId),
                AnanOrganizationAuthRespDto.class);
    }

    @Override
    public List<AnanOrganizationAuthRespDto> findAllByOrganizId(Long organizId) {
        return BeanUtil.copyCollectionProperties(getRepository().findAllByOrganizId(organizId),
                AnanOrganizationAuthRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(RegisterDto registerDto) {
        Date now = new Date();

        //创建机构
        AnanOrganizationCreateDto organization = new AnanOrganizationCreateDto();
        BeanUtils.copyProperties(registerDto.getOrganization(), organization);
        organization.setPid(0L);
        organization.setTopId(0L);
        organization.setStatus(0);
        AnanOrganizationRespDto organizationEntity = organizationService.create(organization);
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
        AnanUserRespDto user = userService.create(createDTO);


        Long versionId = registerDto.getVersionId();
        AnanVersionRespDto respDto = versionService.findOneById(versionId);

        //创建订单
        AnanPayOrderCreateDto orderEntity = new AnanPayOrderCreateDto();
        orderEntity.setMoney(respDto.getPrice());
        orderEntity.setOrderTime(now);
        orderEntity.setVersionId(versionId);
        orderEntity.setOrganizId(organizationEntity.getId());
        orderEntity.setUserId(user.getId());
        if (respDto.getPrice() == 0) {
            orderEntity.setStatus(1);
            orderEntity.setPayTime(now);
        } else {
            orderEntity.setStatus(0);
        }
        AnanPayOrderRespDto payOrderRespDto = payOrderService.create(orderEntity);

        //创建机构认证信息
        AnanOrganizationAuthCreateDto auth = new AnanOrganizationAuthCreateDto();
        auth.setVersionId(versionId);
        auth.setOrderId(payOrderRespDto.getId());
        auth.setMaxOrganizs(respDto.getMaxOrganizs());
        auth.setMaxUsers(respDto.getMaxUsers());
        auth.setOrganizId(organizationEntity.getId());
        auth.setProtectDays(respDto.getProtectDays());
        auth.setTryout(respDto.getTryout());
        auth.setTryoutDays(respDto.getTryoutDays());
        auth.setValidity(respDto.getValidity());
        auth.setAuthorizationCode(getAuthCode(auth));
        this.create(auth);

        return true;
    }

    private String getAuthCode(AnanOrganizationAuthCreateDto auth) {
        return passwordEncoder.encode(auth.toString());
    }
}
