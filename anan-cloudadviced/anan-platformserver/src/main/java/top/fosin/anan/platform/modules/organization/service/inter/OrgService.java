package top.fosin.anan.platform.modules.organization.service.inter;


import top.fosin.anan.cloudresource.dto.res.OrgRespDto;
import top.fosin.anan.cloudresource.dto.res.OrgTreeDto;
import top.fosin.anan.jpa.service.ICrudJpaService;
import top.fosin.anan.jpa.service.IRetrieveTreeJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.entity.Organization;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface OrgService extends ICrudJpaService<OrgReqDto, OrgRespDto, Long, Organization>,
        IRetrieveTreeJpaService<OrgReqDto, OrgTreeDto, Long, Organization> {

}

