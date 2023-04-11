package top.fosin.anan.cloudresource.service.inter.base;

import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;

import java.util.List;

/**
 * 参数基础服务
 *
 * @author fosin
 * @date 2017/12/29
 */
public interface ParameterBaseService {
    void cancelDelete(List<Long> ids);

    ParameterRespDto getParameter(Integer type, String scope, String name);

    ParameterRespDto getNearestParameter(int type, String scope, String name);

    String getOrCreateParameter(int type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChangeAll();

    Boolean applyChanges(List<Long> ids);
}
