package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpPayCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpPayEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付表(cdp_pay)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpPayService extends ISimpleJpaService<CdpPayEntity, Long, CdpPayCreateDto, CdpPayRetrieveDto, CdpPayUpdateDto> {
}
