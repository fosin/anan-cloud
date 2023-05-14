package top.fosin.anan.platform.modules.parameter.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.entity.req.ParameterCreateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.parameter.IParameter;
import top.fosin.anan.cloudresource.parameter.IParameterStrategy;
import top.fosin.anan.cloudresource.parameter.OrganStrategy;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.platform.modules.parameter.service.inter.ParameterService;

import java.util.Objects;

/**
 * 机构参数-本地模式
 *
 * @author fosin
 * @date 2018.8.1
 */
@Service
@Lazy
@AllArgsConstructor
public class LocalOrganParameter implements IParameter {
    private final ParameterService parameterService;
    private final CurrentUserService currentUserService;

    @Override
    public ParameterDTO setParameter(String scope, String name, String value, String description) {
        ParameterCreateDTO createDto = new ParameterCreateDTO();
        createDto.setValue(value);
        createDto.setType(this.getParameterStrategy().getType());
        createDto.setScope(scope);
        createDto.setName(name);
        createDto.setDescription(description);
        return parameterService.create(createDto);
    }

    @Override
    public String getParameter(String scope, String name) {
        return Objects.requireNonNull(parameterService.getParameter(this.getParameterStrategy().getType(), scope, name)).getValue();
    }

    @Override
    public String getNearestParameter(String scope, String name) {
        return Objects.requireNonNull(parameterService.getNearestParameter(this.getParameterStrategy().getType(), scope, name)).getValue();
    }

    @Override
    public String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        return parameterService.getOrCreateParameter(this.getParameterStrategy().getType(),
                scope, name, defaultValue, description);
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return new OrganStrategy(currentUserService);
    }
}
