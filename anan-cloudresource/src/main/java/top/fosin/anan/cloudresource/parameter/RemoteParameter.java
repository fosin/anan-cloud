package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;

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
    public synchronized AnanParameterRespDto setParameter(String scope, String name, String value, String description) {
        AnanParameterReqDto createDto = new AnanParameterReqDto();
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
        AnanParameterReqDto dto = new AnanParameterReqDto();
        dto.setScope(scope);
        dto.setType(this.getParameterStrategy().getType());
        dto.setName(name);
        dto.setValue(defaultValue);
        dto.setDefaultValue(defaultValue);
        dto.setDescription(description);
        return parameterService.getOrCreateParameter(dto).getBody();
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return this.parameterStrategy;
    }
}
