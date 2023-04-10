package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.service.PermissionProxyRpcServiceImpl;
import top.fosin.anan.cloudresource.service.inter.PermissionRpcService;
import top.fosin.anan.core.util.BeanUtil;

/**
 * 远程参数类-远程调用方式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteParameter implements IParameter {
    private final IParameterStrategy parameterStrategy;
    private final PermissionRpcService permissionRpcService;

    public RemoteParameter(IParameterStrategy parameterStrategy) {
        this(parameterStrategy, new PermissionProxyRpcServiceImpl());
    }

    public RemoteParameter(IParameterStrategy parameterStrategy, PermissionRpcService permissionRpcService) {
        this.parameterStrategy = parameterStrategy;
        this.permissionRpcService = permissionRpcService;
    }

    @Override
    public synchronized ParameterRespDto setParameter(String scope, String name, String value, String description) {
        int type = this.getParameterStrategy().getType();
        ParameterRespDto parameter = permissionRpcService.getParameter(type, scope, name);
        parameter.setValue(value);
        parameter.setType(type);
        parameter.setScope(scope);
        parameter.setName(name);
        parameter.setDescription(description);
        permissionRpcService.processUpdate(BeanUtil.copyProperties(parameter, ParameterReqDto.class));
        return parameter;
    }

    @Override
    public String getParameter(String scope, String name) {
        return permissionRpcService.getParameter(this.getParameterStrategy().getType(), scope, name).getValue();
    }

    @Override
    public String getNearestParameter(String scope, String name) {
        return permissionRpcService.getNearestParameter(this.getParameterStrategy().getType(), scope, name)
                .getValue();
    }

    @Override
    public String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        return permissionRpcService.getOrCreateParameter(this.getParameterStrategy().getType(), scope, name, defaultValue, description);
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return this.parameterStrategy;
    }
}
