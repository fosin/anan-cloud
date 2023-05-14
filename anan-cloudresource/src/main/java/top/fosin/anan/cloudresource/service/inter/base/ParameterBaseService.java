package top.fosin.anan.cloudresource.service.inter.base;

import top.fosin.anan.cloudresource.entity.res.ParameterDTO;

import java.util.List;

/**
 * 参数基础服务
 *
 * @author fosin
 * @date 2017/12/29
 */
public interface ParameterBaseService {
    void cancelDelete(List<Long> ids);

    ParameterDTO getParameter(Integer type, String scope, String name);

    ParameterDTO getNearestParameter(int type, String scope, String name);

    String getOrCreateParameter(int type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChangeAll();

    Boolean applyChanges(List<Long> ids);
}
