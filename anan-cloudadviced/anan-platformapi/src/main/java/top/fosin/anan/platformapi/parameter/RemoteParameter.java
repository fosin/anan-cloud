package top.fosin.anan.platformapi.parameter;

import top.fosin.anan.cloudresource.dto.request.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanParameterRetrieveDto;
import top.fosin.anan.platformapi.entity.AnanParameterEntity;
import top.fosin.anan.platformapi.service.inter.ParameterFeignService;


import java.util.Objects;

/**
 * 远程参数类-远程调用方式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteParameter implements IParameter {
    private final IParameterStrategy parameterStrategy;
    private final ParameterFeignService parameterService;

    public RemoteParameter(IParameterStrategy parameterStrategy, ParameterFeignService parameterService) {
        this.parameterStrategy = parameterStrategy;
        this.parameterService = parameterService;
    }

    @Override
    public synchronized AnanParameterEntity setParameter(String scope, String name, String value, String description) {
        AnanParameterCreateDto createDto = new AnanParameterCreateDto();
        createDto.setValue(value);
        createDto.setType(this.getParameterStrategy().getType());
        createDto.setScope(scope);
        createDto.setName(name);
        createDto.setDescription(description);
        return parameterService.create(createDto).getBody();
    }

    @Override
    public String getParameter(String scope, String name) {
        return Objects.requireNonNull(parameterService.getParameter(this.getParameterStrategy().getType(), scope, name).getBody()).getValue();
    }

    @Override
    public String getNearestParameter(String scope, String name) {
        return Objects.requireNonNull(parameterService.getNearestParameter(this.getParameterStrategy().getType(), scope, name).getBody()).getValue();
    }

    @Override
    public String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        AnanParameterRetrieveDto createEntity = new AnanParameterRetrieveDto();
        createEntity.setScope(scope);
        createEntity.setType(this.getParameterStrategy().getType());
        createEntity.setName(name);
        createEntity.setValue(defaultValue);
        createEntity.setDefaultValue(defaultValue);
        createEntity.setDescription(description);
        return parameterService.getOrCreateParameter(createEntity).getBody();
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return this.parameterStrategy;
    }
}
