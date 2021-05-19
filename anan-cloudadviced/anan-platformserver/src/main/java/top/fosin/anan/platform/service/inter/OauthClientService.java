package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.OauthClientDetailsCreateDto;
import top.fosin.anan.platform.dto.req.OauthClientDetailsRetrieveDto;
import top.fosin.anan.platform.dto.req.OauthClientDetailsUpdateDto;
import top.fosin.anan.platform.dto.res.OauthClientDetailsRespDto;
import top.fosin.anan.platform.entity.OauthClientDetailsEntity;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface OauthClientService extends ISimpleJpaService<OauthClientDetailsEntity, OauthClientDetailsRespDto,
        String, OauthClientDetailsCreateDto,
        OauthClientDetailsRetrieveDto,
        OauthClientDetailsUpdateDto> {

}
