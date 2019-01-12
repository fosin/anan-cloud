package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysVersionEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统版本表(cdp_sys_version)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysVersionService extends ISimpleJpaService<CdpSysVersionEntity, Long, CdpSysVersionEntity, CdpSysVersionEntity, CdpSysVersionEntity> {
}