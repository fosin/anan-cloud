package top.fosin.anan.cloudresource.service.inter;

import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;

import javax.validation.constraints.NotNull;

public interface PermissionRpcService extends ParameterBaseService {
    void processUpdate(@NotNull ParameterReqDto reqDto);
}
