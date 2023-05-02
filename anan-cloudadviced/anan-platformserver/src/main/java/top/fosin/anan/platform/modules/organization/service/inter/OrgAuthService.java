package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.cloudresource.entity.req.RegisterDTO;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgAuthRespDto;
import top.fosin.anan.platform.modules.organization.po.OrganizationAuth;

import java.util.List;

/**
 * 系统机构授权系统机构授权表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrgAuthService extends
        ISimpleJpaService<OrgAuthReqDto,OrgAuthRespDto,Long,OrganizationAuth> {
    List<OrgAuthRespDto> findAllByVersionId(Long versionId);

    List<OrgAuthRespDto> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDTO registerDto);
}
