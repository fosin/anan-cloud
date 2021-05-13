package top.fosin.anan.platform.service;

import top.fosin.anan.platform.service.inter.ParameterService;
import top.fosin.anan.platformapi.entity.AnanParameterEntity;
import top.fosin.anan.platformapi.parameter.IParameter;
import top.fosin.anan.platformapi.parameter.IParameterStrategy;
import top.fosin.anan.platformapi.parameter.OrganStrategy;
import top.fosin.anan.cloudresource.dto.request.AnanParameterCreateDto;
import top.fosin.anan.platformapi.service.AnanUserDetailService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *  数据库参数工具类
 *
 * @author fosin
 * @date 2018.8.1
 */
@Service
@Lazy
public class LocalOrganParameter implements IParameter {
    private final ParameterService parameterService;
    private final AnanUserDetailService ananUserDetailService;
    public LocalOrganParameter(ParameterService parameterService, AnanUserDetailService ananUserDetailService) {
        this.parameterService = parameterService;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    public AnanParameterEntity setParameter(String scope, String name, String value, String description) {
        AnanParameterCreateDto createDto = new AnanParameterCreateDto();
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
        return Objects.requireNonNull(parameterService.getOrCreateParameter(this.getParameterStrategy().getType(),
                scope, name, defaultValue, description)).getValue();
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return new OrganStrategy(ananUserDetailService);
    }
}
