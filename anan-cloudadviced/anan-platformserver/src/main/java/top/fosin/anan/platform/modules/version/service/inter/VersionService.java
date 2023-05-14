package top.fosin.anan.platform.modules.version.service.inter;

import top.fosin.anan.jpa.service.ICreateJpaService;
import top.fosin.anan.jpa.service.IDeleteJpaService;
import top.fosin.anan.jpa.service.IRetrieveJpaService;
import top.fosin.anan.jpa.service.IUpdateJpaService;
import top.fosin.anan.platform.modules.version.dto.VersionCreateDTO;
import top.fosin.anan.platform.modules.version.dto.VersionDTO;
import top.fosin.anan.platform.modules.version.dto.VersionUpdateDTO;
import top.fosin.anan.platform.modules.version.po.Version;

        /**
 * 系统版本表(anan_version)服务类
 *
 * @author fosin
 * @date 2023-05-13
 */
public interface VersionService extends 
        ICreateJpaService<VersionCreateDTO, VersionDTO, Long, Version>,
        IRetrieveJpaService<VersionDTO, Long, Version>,
        IUpdateJpaService<VersionUpdateDTO, Long, Version>,
        IDeleteJpaService<Long, Version> {
}

