package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;
import top.fosin.anan.core.util.BeanUtil;

/**
 * 远程参数类-远程调用方式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteParameter implements IParameter {
    private final IParameterStrategy parameterStrategy;
    private final ParameterFeignService parameterFeignService;

    public RemoteParameter(IParameterStrategy parameterStrategy, ParameterFeignService parameterFeignService) {
        this.parameterStrategy = parameterStrategy;
        this.parameterFeignService = parameterFeignService;
    }

    @Override
    public synchronized ParameterRespDto setParameter(String scope, String name, String value, String description) {
        int type = this.getParameterStrategy().getType();
        ParameterRespDto parameter = parameterFeignService.getParameter(type, scope, name).orElseThrow("未找到对应参数type: " + type + "scope: " + scope + "name: " + name);
        parameter.setValue(value);
        parameter.setType(type);
        parameter.setScope(scope);
        parameter.setName(name);
        parameter.setDescription(description);
        return parameterFeignService.processUpdate(BeanUtil.copyProperties(parameter, ParameterReqDto.class)).orElseThrow();
    }

    @Override
    public String getParameter(String scope, String name) {
        return parameterFeignService.getParameter(this.getParameterStrategy().getType(), scope, name)
                .orElseThrow().getValue();
    }

    @Override
    public String getNearestParameter(String scope, String name) {
        return parameterFeignService.getNearestParameter(this.getParameterStrategy().getType(), scope, name)
                .orElseThrow().getValue();
    }

    @Override
    public String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        ParameterReqDto dto = new ParameterReqDto();
        dto.setScope(scope);
        dto.setType(this.getParameterStrategy().getType());
        dto.setName(name);
        dto.setValue(defaultValue);
        dto.setDefaultValue(defaultValue);
        dto.setDescription(description);
        return parameterFeignService.getOrCreateParameter(dto).orElseThrow();
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return this.parameterStrategy;
    }
}
