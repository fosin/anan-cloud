package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpSysPayDetailCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysPayDetailRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysPayDetailUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpSysPayDetailEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付明细表(cdp_sys_pay_detail)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysPayDetailService extends ISimpleJpaService<CdpSysPayDetailEntity, Long, CdpSysPayDetailCreateDto, CdpSysPayDetailRetrieveDto, CdpSysPayDetailUpdateDto> {
}