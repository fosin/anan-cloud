package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.jpa.service.IStatusJpaService;

import top.fosin.anan.platform.dto.req.AnanOrganizationCreateDto;
import top.fosin.anan.platform.dto.req.AnanOrganizationRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanOrganizationUpdateDto;
import top.fosin.anan.platform.entity.AnanOrganizationEntity;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface OrganizationService extends ISimpleJpaService<AnanOrganizationEntity,
        AnanOrganizationRespDto,
        Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto>,
        IRetrieveTreeJpaService<AnanOrganizationEntity, AnanOrganizationTreeDto,
                Long, AnanOrganizationRetrieveDto>,
        IStatusJpaService<AnanOrganizationEntity,AnanOrganizationRespDto, Long, Integer> {

    AnanOrganizationTreeDto treeAllChildByid(Long id);
}

