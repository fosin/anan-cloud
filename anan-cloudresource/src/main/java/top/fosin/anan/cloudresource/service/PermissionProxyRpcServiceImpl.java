package top.fosin.anan.cloudresource.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import top.fosin.anan.cloudresource.entity.req.ParameterReqDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterRespDTO;
import top.fosin.anan.cloudresource.service.inter.feign.ParameterFeignService;
import top.fosin.anan.cloudresource.service.inter.rpc.ParameterRpcService;
import top.fosin.anan.cloudresource.type.RpcCallStrategy;

import java.util.List;


public class PermissionProxyRpcServiceImpl implements ParameterRpcService {
    private ApplicationContext ac;

    @Value("anan.cloud.rpc.strategy")
    private RpcCallStrategy rpcStrategy = RpcCallStrategy.GRPC;
    private ParameterFeignService parameterFeignService;
    private ParameterRpcService parameterRpcService;

    private ApplicationContext getApplicationContext() {
        if (ac == null) {
            ac = ContextLoader.getCurrentWebApplicationContext();
        }
        return ac;
    }

    private ParameterFeignService getParameterFeignService() {
        if (parameterFeignService == null) {
            parameterFeignService = getApplicationContext().getBean(ParameterFeignService.class);
        }
        return parameterFeignService;
    }

    private ParameterRpcService getPermissionRpcService() {
        if (parameterRpcService == null) {
            parameterRpcService = getApplicationContext().getBean(ParameterRpcService.class);
        }
        return parameterRpcService;
    }

    @Override
    public void cancelDelete(List<Long> ids) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            getPermissionRpcService().cancelDelete(ids);
        } else {
            getParameterFeignService().cancelDelete(ids);
        }
    }

    @Override
    public ParameterRespDTO getParameter(Integer type, String scope, String name) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            return getPermissionRpcService().getParameter(type, scope, name);
        } else {
            return getParameterFeignService().getParameter(type, scope, name).getData();
        }
    }

    @Override
    public ParameterRespDTO getNearestParameter(int type, String scope, String name) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            return getPermissionRpcService().getNearestParameter(type, scope, name);
        } else {
            return getParameterFeignService().getNearestParameter(type, scope, name).getData();
        }
    }

    @Override
    public String getOrCreateParameter(int type, String scope, String name, String defaultValue, String description) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            return getPermissionRpcService().getOrCreateParameter(type, scope, name, defaultValue, description);
        } else {
            ParameterReqDTO reqDto = new ParameterReqDTO();
            reqDto.setType(type);
            reqDto.setScope(scope);
            reqDto.setName(name);
            reqDto.setDefaultValue(defaultValue);
            reqDto.setDescription(description);
            return getParameterFeignService().getOrCreateParameter(reqDto).getData();
        }
    }

    @Override
    public Boolean applyChange(Long id) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            return getPermissionRpcService().applyChange(id);
        } else {
            return getParameterFeignService().applyChange(id).getData();
        }
    }

    @Override
    public Boolean applyChangeAll() {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            return getPermissionRpcService().applyChangeAll();
        } else {
            return getParameterFeignService().applyChangeAll().getData();
        }
    }

    @Override
    public Boolean applyChanges(List<Long> ids) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            return getPermissionRpcService().applyChanges(ids);
        } else {
            return getParameterFeignService().applyChanges(ids).getData();
        }
    }

    @Override
    public void processUpdate(ParameterReqDTO reqDto) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            getPermissionRpcService().processUpdate(reqDto);
        } else {
            getParameterFeignService().processUpdate(reqDto);
        }
    }
}
