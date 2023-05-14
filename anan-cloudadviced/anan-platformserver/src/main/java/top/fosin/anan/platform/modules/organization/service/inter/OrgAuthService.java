package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.cloudresource.entity.req.RegisterDTO;
import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthCreateDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationAuthUpdateDTO;
import top.fosin.anan.platform.modules.organization.po.OrganizationAuth;

import java.util.List;

/**
 * 系统机构授权系统机构授权表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrgAuthService extends
        ICreateJpaService<OrganizationAuthCreateDTO, OrganizationAuthDTO, Long, OrganizationAuth>,
        IRetrieveJpaService<OrganizationAuthDTO, Long, OrganizationAuth>,
        IUpdateJpaService<OrganizationAuthUpdateDTO, Long, OrganizationAuth>,
        IDeleteJpaService<Long, OrganizationAuth> {
    List<OrganizationAuthDTO> findAllByVersionId(Long versionId);

    List<OrganizationAuthDTO> findAllByOrganizId(Long organizId);

    Boolean register(RegisterDTO registerDto);
}
