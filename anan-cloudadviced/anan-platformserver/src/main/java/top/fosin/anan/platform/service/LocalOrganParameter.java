package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.cloudresource.parameter.IParameter;
import top.fosin.anan.cloudresource.parameter.IParameterStrategy;
import top.fosin.anan.cloudresource.parameter.OrganStrategy;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.platform.service.inter.ParameterService;

import java.util.Objects;

/**
 * 数据库参数工具类
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
    public AnanParameterRespDto setParameter(String scope, String name, String value, String description) {
        AnanParameterReqDto createDto = new AnanParameterReqDto();
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
