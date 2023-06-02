package top.fosin.anan.platform.modules.organization.service.inter;


import top.fosin.anan.cloudresource.entity.res.OrganizTreeDTO;
import top.fosin.anan.jpa.service.*;
import top.fosin.anan.platform.modules.organization.dto.OrganizationCreateDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationUpdateDTO;
import top.fosin.anan.platform.modules.organization.po.Organization;
import top.fosin.anan.platform.modules.organization.query.OrganizationQuery;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface OrgService extends
        ICreateJpaService<OrganizationCreateDTO, OrganizationDTO, Long, Organization>,
        IRetrieveJpaService<OrganizationDTO, Long, Organization>,
        IUpdateJpaService<OrganizationUpdateDTO, Long, Organization>,
        IDeleteJpaService<Long, Organization>,
        IRetrieveTreeJpaService<OrganizationQuery, OrganizTreeDTO, Long, Organization> {

    List<Organization> findByTopIdAndCodeStartingWithOrderByCodeAsc(Long topId, String code);
}

