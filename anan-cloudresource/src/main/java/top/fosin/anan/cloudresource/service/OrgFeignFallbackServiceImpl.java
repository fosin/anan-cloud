package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.service.inter.feign.OrgFeignService;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.SingleResult;

import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class OrgFeignFallbackServiceImpl implements OrgFeignService {

    @Override
    public MultResult<OrganizRespDTO> listChild(Long pid, String version) {
        log.error("feign 根据父序号pid远程其直接子节点的数据集合:{}", pid);
        return null;
    }

    @Override
    public MultResult<OrganizRespDTO> listByIds(List<Long> ids, String version) {
        log.error("feign 根据用户序号集合远程查询多条数据:{}", ids);
        return null;
    }

    @Override
    public MultResult<OrganizRespDTO> listAllChild(Long pid, String version) {
        log.error("feign 根据父序号pid远程其所有孩子数据集合失败:{}", pid);
        return null;
    }

    @Override
    public MultResult<OrganizRespDTO> treeAllChild(Long topId, String version) {
        log.error("feign 根据父序号pid远程查询其所有孩子数据，并构建树型对象失败:{}", topId);
        return null;
    }

    @Override
    public SingleResult<OrganizRespDTO> findOneById(Long id, String version) {
        log.error("feign 根据主键序号远程查询一条数据:{}", id);
        return null;
    }

}
