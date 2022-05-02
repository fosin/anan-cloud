package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanOrganizationReqDto;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface OrganizationService extends ISimpleJpaService<AnanOrganizationEntity,
        AnanOrganizationRespDto,
        Long, AnanOrganizationReqDto, AnanOrganizationReqDto, AnanOrganizationReqDto>,
        IRetrieveTreeJpaService<AnanOrganizationEntity, AnanOrganizationTreeDto,
                Long, AnanOrganizationReqDto> {

    AnanOrganizationTreeDto treeAllChildByid(Long id);
}

