package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationAuth;

import java.util.List;

/**
 * 系统机构授权系统机构授权表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrganizationAuthService extends
        ISimpleJpaService<OrganizationAuth,
                OrganizationAuthRespDto,
                Long, OrganizationAuthReqDto,
                OrganizationAuthReqDto, OrganizationAuthReqDto> {
    List<OrganizationAuthRespDto> findAllByVersionId(Long versionId);

    List<OrganizationAuthRespDto> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDto registerDto);
}
