package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationAuth;

import java.util.List;

/**
 * 系统机构授权系统机构授权表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrgAuthService extends
        ISimpleJpaService<OrganizationAuth,
                OrgAuthRespDto,
                Long, OrgAuthReqDto,
                OrgAuthReqDto, OrgAuthReqDto> {
    List<OrgAuthRespDto> findAllByVersionId(Long versionId);

    List<OrgAuthRespDto> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDto registerDto);
}
