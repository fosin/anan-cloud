package top.fosin.anan.cloudresource.service.inter.rpc;

import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.service.inter.base.ParameterBaseService;

public interface ParameterRpcService extends ParameterBaseService {
    void processUpdate(ParameterUpdateDTO reqDto);
}
