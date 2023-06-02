package top.fosin.anan.cloudresource.service.inter.base;

import org.springframework.util.StringUtils;
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

    ParameterDTO getParameter(Byte type, String scope, String name);

    ParameterDTO getNearestParameter(Byte type, String scope, String name);

    String getOrCreateParameter(Byte type, String scope, String name, String defaultValue, String description);

    Boolean applyChange(Long id);

    Boolean applyChangeAll();

    Boolean applyChanges(List<Long> ids);

    default String getCacheKey(Byte type, String scope, String name) {
        if (!StringUtils.hasText(scope)) {
            scope = "";
        }
        return type + "-" + scope + "-" + name;
    }

    default String getCacheKey(ParameterDTO dto) {
        return getCacheKey(dto.getType(), dto.getScope(), dto.getName());
    }

}
