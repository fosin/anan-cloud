package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.req.RegisterDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanOrganizationAuthReqDto;
import top.fosin.anan.platform.dto.res.AnanOrganizationAuthRespDto;
import top.fosin.anan.platform.entity.AnanOrganizationAuthEntity;

import java.util.List;

/**
 * 系统机构授权系统机构授权表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrganizationAuthService extends
        ISimpleJpaService<AnanOrganizationAuthEntity,
                AnanOrganizationAuthRespDto,
                Long, AnanOrganizationAuthReqDto,
                AnanOrganizationAuthReqDto, AnanOrganizationAuthReqDto> {
    List<AnanOrganizationAuthRespDto> findAllByVersionId(Long versionId);

    List<AnanOrganizationAuthRespDto> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDto registerDto);
}
