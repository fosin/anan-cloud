package top.fosin.anan.platform.service.inter;

import top.fosin.anan.cloudresource.dto.res.AnanServiceRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.jpa.service.IStatusJpaService;
import top.fosin.anan.model.dto.StatusDto;
import top.fosin.anan.platform.dto.req.AnanServiceCreateDto;
import top.fosin.anan.platform.dto.req.AnanServiceRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanServiceUpdateDto;
import top.fosin.anan.platform.entity.AnanServiceEntity;

import java.util.List;

/**
 * 系统服务表服务接口
 *
 * @author fosin
 * @date 2020-12-04 17:47:40
 */
public interface ServiceService extends ISimpleJpaService<AnanServiceEntity, AnanServiceRespDto,
        Long, AnanServiceCreateDto, AnanServiceRetrieveDto, AnanServiceUpdateDto>,
        IStatusJpaService<AnanServiceEntity, Long, Integer, StatusDto<Long, Integer>> {
    List<AnanServiceRespDto> findAllByStatus(Integer status);
}
