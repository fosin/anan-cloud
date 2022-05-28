package top.fosin.anan.platform.modules.organization.service.inter;


import top.fosin.anan.cloudresource.dto.res.OrgTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationInuse;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface OrgEntryService extends
        IRetrieveTreeJpaService<OrganizationInuse, OrgTreeDto,
                Long, OrgReqDto> {

    OrgTreeDto treeAllChildByid(Long id);
}

