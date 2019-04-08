package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpPayDetailCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayDetailRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpPayDetailUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpPayDetailEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付明细表(cdp_pay_detail)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface CdpPayDetailService extends ISimpleJpaService<CdpPayDetailEntity, Long, CdpPayDetailCreateDto, CdpPayDetailRetrieveDto, CdpPayDetailUpdateDto> {
}
