package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platformapi.entity.AnanServiceEntity;
import top.fosin.anan.platform.dto.request.AnanServiceCreateDto;
import top.fosin.anan.platform.dto.request.AnanServiceRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanServiceUpdateDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;

import java.util.List;

/**
 * 系统服务表(anan_service)表服务接口
 *
 * @author fosin
 * @date 2020-12-04 17:47:40
 */
public interface AnanServiceService extends ISimpleJpaService<AnanServiceEntity, Integer,
        AnanServiceCreateDto, AnanServiceRetrieveDto, AnanServiceUpdateDto> {
    List<AnanServiceEntity> findAllByStatus(Integer status);
}
