package top.fosin.anan.cloudresource.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;
import top.fosin.anan.cloudresource.service.inter.PermissionProxyService;
import top.fosin.anan.cloudresource.type.RpcCallStrategy;

import java.util.List;


public class PermissionProxyServiceImpl implements PermissionProxyService, ApplicationContextAware {
    private ApplicationContext ac;

    @Value("anan.cloud.rpc.strategy")
    private RpcCallStrategy rpcStrategy = RpcCallStrategy.GRPC;

    @Override
    public void cancelDelete(List<Long> ids) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            service.cancelDelete(ids);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            service.cancelDelete(ids);
        }
    }

    @Override
    public ParameterRespDto getParameter(Integer type, String scope, String name) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            return service.getParameter(type, scope, name);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            return service.getParameter(type, scope, name).getData();
        }
    }

    @Override
    public ParameterRespDto getNearestParameter(int type, String scope, String name) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            return service.getNearestParameter(type, scope, name);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            return service.getNearestParameter(type, scope, name).getData();
        }
    }

    @Override
    public String getOrCreateParameter(int type, String scope, String name, String defaultValue, String description) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            return service.getOrCreateParameter(type, scope, name, defaultValue, description);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            ParameterReqDto reqDto = new ParameterReqDto();
            reqDto.setType(type);
            reqDto.setScope(scope);
            reqDto.setName(name);
            reqDto.setDefaultValue(defaultValue);
            reqDto.setDescription(description);
            return service.getOrCreateParameter(reqDto).getData();
        }
    }

    @Override
    public Boolean applyChange(Long id) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            return service.applyChange(id);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            return service.applyChange(id).getData();
        }
    }

    @Override
    public Boolean applyChangeAll() {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            return service.applyChangeAll();
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            return service.applyChangeAll().getData();
        }
    }

    @Override
    public Boolean applyChanges(List<Long> ids) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            return service.applyChanges(ids);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            return service.applyChanges(ids).getData();
        }
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    @Override
    public void processUpdate(ParameterReqDto reqDto) {
        if (rpcStrategy == RpcCallStrategy.GRPC) {
            PermissionProxyService service = ac.getBean(PermissionProxyService.class);
            service.processUpdate(reqDto);
        } else {
            ParameterFeignService service = ac.getBean(ParameterFeignService.class);
            service.processUpdate(reqDto);
        }
    }
}
