package top.fosin.anan.platform.modules.pub.service.inter;

import top.fosin.anan.cloudresource.dto.res.ServiceRespDto;
import top.fosin.anan.jpa.service.ISimpleJpaService;
import top.fosin.anan.platform.modules.pub.dto.ServiceReqDto;
import top.fosin.anan.platform.modules.pub.po.Service;

/**
 * 系统服务表服务接口
 *
 * @author fosin
 * @date 2020-12-04 17:47:40
 */
public interface ServiceService extends ISimpleJpaService<ServiceReqDto, ServiceRespDto, Long,Service> {
}
