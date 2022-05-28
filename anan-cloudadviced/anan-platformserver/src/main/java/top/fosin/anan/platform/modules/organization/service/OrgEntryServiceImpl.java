package top.fosin.anan.platform.modules.organization.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.res.OrgTreeDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.RelaOperator;
import top.fosin.anan.model.module.RelaQueryRule;
import top.fosin.anan.platform.modules.organization.dao.OrgInuseDao;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationInuse;
import top.fosin.anan.platform.modules.organization.service.inter.OrgEntryService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class OrgEntryServiceImpl implements OrgEntryService {
    private final OrgInuseDao orgInuseDao;

    @Override
    public List<OrgTreeDto> listChild(Long pid) {
        return BeanUtil.copyProperties(orgInuseDao.findByPidOrderByCodeAsc(pid),
                OrgTreeDto.class);
    }

    @Override
    public List<OrgTreeDto> listAllChild(Long pid) {
        List<OrganizationInuse> result = new ArrayList<>();
        if (pid == 0) {
            List<OrganizationInuse> list = orgInuseDao.findByPidOrderByCodeAsc(pid);
            for (OrganizationInuse organization : list) {
                List<OrganizationInuse> byCodeStartingWith = orgInuseDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organization.getTopId(), organization.getCode());
                result.addAll(byCodeStartingWith);
            }
        } else {
            OrganizationInuse organization = orgInuseDao.findById(pid).orElse(null);
            if (organization != null) {
                List<OrganizationInuse> byCodeStartingWith = orgInuseDao.findByTopIdAndCodeStartingWithOrderByCodeAsc(organization.getTopId(), organization.getCode());
                result.addAll(byCodeStartingWith);
            }
        }
        return BeanUtil.copyProperties(result, OrgTreeDto.class);
    }

    @Override
    public OrgTreeDto treeAllChildByid(Long id) {
        OrganizationInuse entity = orgInuseDao.findById(id).orElseThrow(() -> new IllegalArgumentException("根据ID查询不到数据!"));
        OrgReqDto dto = new OrgReqDto();
        dto.setTopId(entity.getTopId());
        dto.setCode(entity.getCode());
        LogicalQueryRule logicalQueryRule = new LogicalQueryRule();
        List<RelaQueryRule> relaQueryRules = new ArrayList<>();
        logicalQueryRule.setRelaRules(relaQueryRules);
        relaQueryRules.add(new RelaQueryRule(RelaOperator.rightLike, "code"));
        relaQueryRules.add(new RelaQueryRule(RelaOperator.eq, "topId"));
        dto.setQueryRule(logicalQueryRule);
        return this.treeAllChild(dto, id);
    }

    @Override
    public IJpaRepository<OrganizationInuse, Long> getDao() {
        return orgInuseDao;
    }
}
