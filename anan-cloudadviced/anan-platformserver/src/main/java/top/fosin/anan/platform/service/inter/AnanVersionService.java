package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanVersionRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanVersionCreateDto;
import top.fosin.anan.platform.dto.req.AnanVersionRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanVersionUpdateDto;
import top.fosin.anan.platform.entity.AnanVersionEntity;

/**
 * 系统版本表(anan_version)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionService extends ISimpleJpaService<AnanVersionEntity, AnanVersionRespDto,
        Long, AnanVersionCreateDto, AnanVersionRetrieveDto, AnanVersionUpdateDto> {
}
