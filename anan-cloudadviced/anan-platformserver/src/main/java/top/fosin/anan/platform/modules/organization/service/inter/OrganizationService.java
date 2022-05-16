package top.fosin.anan.platform.modules.organization.service.inter;


import top.fosin.anan.cloudresource.dto.res.OrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.OrganizationTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrganizationReqDto;
import top.fosin.anan.platform.modules.organization.entity.Organization;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface OrganizationService extends ISimpleJpaService<Organization,
        OrganizationRespDto,
        Long, OrganizationReqDto, OrganizationReqDto, OrganizationReqDto>,
        IRetrieveTreeJpaService<Organization, OrganizationTreeDto,
                Long, OrganizationReqDto> {

    OrganizationTreeDto treeAllChildByid(Long id);
}

