package top.fosin.anan.cloudresource.service.inter.rpc;

import top.fosin.anan.cloudresource.entity.req.ParameterReqDTO;
import top.fosin.anan.cloudresource.service.inter.base.ParameterBaseService;

import javax.validation.constraints.NotNull;

public interface ParameterRpcService extends ParameterBaseService {
    void processUpdate(@NotNull ParameterReqDTO reqDto);
}
