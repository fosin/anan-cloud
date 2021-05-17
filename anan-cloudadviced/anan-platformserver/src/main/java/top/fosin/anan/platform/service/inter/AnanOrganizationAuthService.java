package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanOrganizationAuthRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.request.AnanOrganizationAuthCreateDto;
import top.fosin.anan.platform.dto.request.AnanOrganizationAuthRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanOrganizationAuthUpdateDto;
import top.fosin.anan.platform.entity.AnanOrganizationAuthEntity;
import top.fosin.anan.cloudresource.dto.req.RegisterDto;

import java.util.List;

/**
 * 系统机构授权表(anan_organization_auth)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanOrganizationAuthService extends
        ISimpleJpaService<AnanOrganizationAuthEntity,
                AnanOrganizationAuthRespDto,
                Long, AnanOrganizationAuthCreateDto,
                AnanOrganizationAuthRetrieveDto, AnanOrganizationAuthUpdateDto> {
    List<AnanOrganizationAuthRespDto> findAllByVersionId(Long versionId);

    List<AnanOrganizationAuthRespDto> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDto registerDto);
}
