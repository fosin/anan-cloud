package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.service.PermissionProxyRpcServiceImpl;
import top.fosin.anan.cloudresource.service.inter.rpc.ParameterRpcService;
import top.fosin.anan.core.util.BeanUtil;

/**
 * 远程参数类-远程调用方式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteParameter implements IParameter {
    private final IParameterStrategy parameterStrategy;
    private final ParameterRpcService parameterRpcService;

    public RemoteParameter(IParameterStrategy parameterStrategy) {
        this(parameterStrategy, new PermissionProxyRpcServiceImpl());
    }

    public RemoteParameter(IParameterStrategy parameterStrategy, ParameterRpcService parameterRpcService) {
        this.parameterStrategy = parameterStrategy;
        this.parameterRpcService = parameterRpcService;
    }

    @Override
    public synchronized ParameterDTO setParameter(String scope, String name, String value, String description) {
        byte type = this.getParameterStrategy().getType();
        ParameterDTO parameter = parameterRpcService.getParameter(type, scope, name);
        parameter.setValue(value);
        parameter.setType(type);
        parameter.setScope(scope);
        parameter.setName(name);
        parameter.setDescription(description);
        parameterRpcService.processUpdate(BeanUtil.copyProperties(parameter, ParameterUpdateDTO.class));
        return parameter;
    }

    @Override
    public String getParameter(String scope, String name) {
        return parameterRpcService.getParameter(this.getParameterStrategy().getType(), scope, name).getValue();
    }

    @Override
    public String getNearestParameter(String scope, String name) {
        return parameterRpcService.getNearestParameter(this.getParameterStrategy().getType(), scope, name)
                .getValue();
    }

    @Override
    public String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        return parameterRpcService.getOrCreateParameter(this.getParameterStrategy().getType(), scope, name, defaultValue, description);
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return this.parameterStrategy;
    }
}
