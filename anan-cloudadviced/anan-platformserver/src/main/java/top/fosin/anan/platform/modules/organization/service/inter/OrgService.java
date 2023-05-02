package top.fosin.anan.platform.modules.organization.service.inter;


import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.entity.res.OrganizTreeDTO;
import top.fosin.anan.jpa.service.ICrudJpaService;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.po.Organization;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface OrgService extends ICrudJpaService<OrgReqDto, OrganizRespDTO, Long, Organization>,
        IRetrieveTreeJpaService<OrgReqDto, OrganizTreeDTO, Long, Organization> {

}

