package top.fosin.anan.cloudresource.service.inter.rpc;

import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.service.inter.base.ParameterBaseService;

import javax.validation.constraints.NotNull;

public interface ParameterRpcService extends ParameterBaseService {
    void processUpdate(@NotNull ParameterReqDto reqDto);
}
