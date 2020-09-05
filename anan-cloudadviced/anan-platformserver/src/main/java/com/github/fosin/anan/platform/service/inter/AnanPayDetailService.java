package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.platform.dto.request.AnanPayDetailCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayDetailRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayDetailUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayDetailEntity;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付明细表(anan_pay_detail)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayDetailService extends ISimpleJpaService<AnanPayDetailEntity, Long, AnanPayDetailCreateDto, AnanPayDetailRetrieveDto, AnanPayDetailUpdateDto> {
}
