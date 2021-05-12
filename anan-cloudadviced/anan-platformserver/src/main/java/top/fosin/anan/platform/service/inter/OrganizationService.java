package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.request.AnanOrganizationCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanOrganizationRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanOrganizationUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platformapi.entity.AnanOrganizationEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface OrganizationService extends ISimpleJpaService<AnanOrganizationEntity,
        Long, AnanOrganizationCreateDto, AnanOrganizationRetrieveDto, AnanOrganizationUpdateDto>,
        IRetrieveTreeJpaService<AnanOrganizationEntity, AnanOrganizationTreeDto,
                Long, AnanOrganizationRetrieveDto> {

    List<AnanOrganizationEntity> findChildByPid(Long pid);

    List<AnanOrganizationEntity> findAllChildByPid(Long pid);

    AnanOrganizationTreeDto treeAllChildByid(Long id);
}

