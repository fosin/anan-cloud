package top.fosin.anan.cloudresource.parameter;

import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.service.PermissionProxyServiceImpl;
import top.fosin.anan.core.util.BeanUtil;

/**
 * 远程参数类-远程调用方式
 *
 * @author fosin
 * @date 2019/5/13
 */
public class RemoteParameter implements IParameter {
    private final IParameterStrategy parameterStrategy;
    private final PermissionProxyServiceImpl permissionProxyService;

    public RemoteParameter(IParameterStrategy parameterStrategy) {
        this.parameterStrategy = parameterStrategy;
        this.permissionProxyService = new PermissionProxyServiceImpl();
    }

    @Override
    public synchronized ParameterRespDto setParameter(String scope, String name, String value, String description) {
        int type = this.getParameterStrategy().getType();
        ParameterRespDto parameter = permissionProxyService.getParameter(type, scope, name);
        parameter.setValue(value);
        parameter.setType(type);
        parameter.setScope(scope);
        parameter.setName(name);
        parameter.setDescription(description);
        permissionProxyService.processUpdate(BeanUtil.copyProperties(parameter, ParameterReqDto.class));
        return parameter;
    }

    @Override
    public String getParameter(String scope, String name) {
        return permissionProxyService.getParameter(this.getParameterStrategy().getType(), scope, name).getValue();
    }

    @Override
    public String getNearestParameter(String scope, String name) {
        return permissionProxyService.getNearestParameter(this.getParameterStrategy().getType(), scope, name)
                .getValue();
    }

    @Override
    public String getOrCreateParameter(String scope, String name, String defaultValue, String description) {
        return permissionProxyService.getOrCreateParameter(this.getParameterStrategy().getType(), scope, name, defaultValue, description);
    }

    @Override
    public IParameterStrategy getParameterStrategy() {
        return this.parameterStrategy;
    }
}
