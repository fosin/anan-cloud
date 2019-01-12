package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysPayDetailEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统支付明细表(cdp_sys_pay_detail)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysPayDetailService extends ISimpleJpaService<CdpSysPayDetailEntity, Long, CdpSysPayDetailEntity, CdpSysPayDetailEntity, CdpSysPayDetailEntity> {
}