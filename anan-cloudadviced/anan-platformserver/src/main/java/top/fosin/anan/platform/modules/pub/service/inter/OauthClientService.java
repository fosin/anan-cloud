package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsReqDto;
import top.fosin.anan.platform.modules.pub.dto.OauthClientDetailsRespDto;
import top.fosin.anan.platform.modules.pub.po.OauthClientDetails;

/**
 * @author fosin
 * @date 2017/12/29
 */
public interface OauthClientService extends ISimpleJpaService<OauthClientDetailsReqDto, OauthClientDetailsRespDto, String,OauthClientDetails> {

}
