package top.fosin.anan.platform.modules.organization.service;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.dto.req.UserRegisterDto;
import top.fosin.anan.cloudresource.dto.req.UserReqDto;
import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.cloudresource.dto.res.OrgRespDto;
import top.fosin.anan.cloudresource.dto.res.UserRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.pay.dto.PayOrderReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthRespDto;
import top.fosin.anan.platform.modules.pay.dto.PayOrderRespDto;
import top.fosin.anan.platform.modules.version.dto.VersionRespDto;
import top.fosin.anan.platform.modules.organization.dao.OrgAuthDao;
import top.fosin.anan.platform.modules.organization.service.inter.OrgAuthService;
import top.fosin.anan.platform.modules.organization.service.inter.OrgService;
import top.fosin.anan.platform.modules.pay.service.inter.PayOrderService;
import top.fosin.anan.platform.modules.user.service.inter.UserService;
import top.fosin.anan.platform.modules.version.service.inter.VersionService;

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
public class OrgAuthServiceImpl implements OrgAuthService {
    private final OrgAuthDao ananSysOrgAuthDao;

    private final UserService userService;

    private final PayOrderService payOrderService;

    private final VersionService versionService;
    private final OrgService orgService;

    private final PasswordEncoder passwordEncoder;

    public OrgAuthServiceImpl(OrgAuthDao ananSysOrgAuthDao, UserService userService, PayOrderService payOrderService, VersionService versionService, OrgService orgService, PasswordEncoder passwordEncoder) {
        this.ananSysOrgAuthDao = ananSysOrgAuthDao;
        this.userService = userService;
        this.payOrderService = payOrderService;
        this.versionService = versionService;
        this.orgService = orgService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 获取DAO
     */
    @Override
    public OrgAuthDao getDao() {
        return ananSysOrgAuthDao;
    }

    @Override
    public List<OrgAuthRespDto> findAllByVersionId(Long versionId) {
        return BeanUtil.copyProperties(getDao().findAllByVersionId(versionId),
                OrgAuthRespDto.class);
    }

    @Override
    public List<OrgAuthRespDto> findAllByOrganizId(Long organizId) {
        return BeanUtil.copyProperties(getDao().findAllByOrganizId(organizId),
                OrgAuthRespDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(RegisterDto registerDto) {
        Date now = new Date();

        //创建机构
        OrgReqDto organization = new OrgReqDto();
        BeanUtils.copyProperties(registerDto.getOrganization(), organization);
        organization.setPid(0L);
        organization.setTopId(0L);
        organization.setStatus(0);
        OrgRespDto organizationEntity = orgService.create(organization);
        OrgReqDto updateDto = new OrgReqDto();
        BeanUtils.copyProperties(organizationEntity, updateDto);
        updateDto.setTopId(organizationEntity.getId());
        orgService.update(updateDto);

        //创建用户
        UserRegisterDto registerDTO = registerDto.getUser();
        UserReqDto createDTO = new UserReqDto();
        BeanUtils.copyProperties(registerDTO, createDTO);
        createDTO.setOrganizId(updateDto.getId());

        try {
            createDTO.setExpireTime(new SimpleDateFormat(DateTimeUtil.DATETIME_PATTERN).parse("2050-12-31 23:59:59"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Assert.isTrue(createDTO.getPassword().equals(createDTO.getConfirmPassword()), "密码和确认密码必须一致!");
        UserRespDto user = userService.create(createDTO);


        Long versionId = registerDto.getVersionId();
        VersionRespDto respDto = versionService.findOneById(versionId);

        //创建订单
        PayOrderReqDto orderEntity = new PayOrderReqDto();
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
        PayOrderRespDto payOrderRespDto = payOrderService.create(orderEntity);

        //创建机构认证信息
        OrgAuthReqDto auth = new OrgAuthReqDto();
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

    private String getAuthCode(OrgAuthReqDto auth) {
        return passwordEncoder.encode(auth.toString());
    }
}
