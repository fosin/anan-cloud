package top.fosin.anan.platform.modules.version.service.inter;

import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.version.dto.VersionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRespDto;
import top.fosin.anan.platform.modules.version.entity.Version;

/**
 * 系统版本表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionService extends ISimpleJpaService<VersionReqDto, VersionRespDto, Long,Version> {

}
