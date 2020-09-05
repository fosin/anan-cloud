package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.platform.dto.request.AnanPayCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanPayRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanPayUpdateDto;
import com.github.fosin.anan.platform.entity.AnanPayEntity;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统支付表(anan_pay)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanPayService extends ISimpleJpaService<AnanPayEntity, Long, AnanPayCreateDto, AnanPayRetrieveDto, AnanPayUpdateDto> {
}
