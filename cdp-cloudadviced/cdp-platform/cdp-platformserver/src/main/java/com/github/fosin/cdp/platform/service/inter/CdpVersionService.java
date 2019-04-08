package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpVersionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionEntity;

/**
 * 系统版本表(cdp_version)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface CdpVersionService extends ISimpleJpaService<CdpVersionEntity, Long, CdpVersionCreateDto, CdpVersionRetrieveDto, CdpVersionUpdateDto> {
}
