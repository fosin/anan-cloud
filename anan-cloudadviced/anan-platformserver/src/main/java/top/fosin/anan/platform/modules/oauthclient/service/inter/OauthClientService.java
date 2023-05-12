package top.fosin.anan.platform.modules.oauthclient.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.oauthclient.dto.OauthClientDetailsReqDto;
import top.fosin.anan.platform.modules.oauthclient.dto.OauthClientDetailsRespDto;
import top.fosin.anan.platform.modules.oauthclient.po.OauthClientDetails;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface OauthClientService extends ISimpleJpaService<OauthClientDetailsReqDto, OauthClientDetailsRespDto, String,OauthClientDetails> {

}
