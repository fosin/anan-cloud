package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.platform.dto.request.AnanVersionCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanVersionEntity;

/**
 * 系统版本表(anan_version)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionService extends ISimpleJpaService<AnanVersionEntity, Long, AnanVersionCreateDto, AnanVersionRetrieveDto, AnanVersionUpdateDto> {
}
