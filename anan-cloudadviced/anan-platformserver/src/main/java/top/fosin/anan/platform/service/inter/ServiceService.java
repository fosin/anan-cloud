package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.res.AnanServiceRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;

import top.fosin.anan.platform.dto.req.AnanServiceReqDto;
import top.fosin.anan.platform.dto.req.AnanServiceReqDto;
import top.fosin.anan.platform.dto.req.AnanServiceReqDto;
import top.fosin.anan.platform.entity.AnanServiceEntity;

/**
 * 系统服务表服务接口
 *
 * @author fosin
 * @date 2020-12-04 17:47:40
 */
public interface ServiceService extends ISimpleJpaService<AnanServiceEntity, AnanServiceRespDto,
        Long, AnanServiceReqDto, AnanServiceReqDto, AnanServiceReqDto> {
}
