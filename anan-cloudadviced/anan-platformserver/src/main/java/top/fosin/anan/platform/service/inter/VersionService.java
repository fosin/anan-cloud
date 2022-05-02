package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.dto.req.AnanVersionReqDto;
import top.fosin.anan.platform.dto.res.AnanVersionRespDto;
import top.fosin.anan.platform.entity.AnanVersionEntity;

/**
 * 系统版本表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionService extends ISimpleJpaService<AnanVersionEntity, AnanVersionRespDto,
        Long, AnanVersionReqDto, AnanVersionReqDto, AnanVersionReqDto> {
}
