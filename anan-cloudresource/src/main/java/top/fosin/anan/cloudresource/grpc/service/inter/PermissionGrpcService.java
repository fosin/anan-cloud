package top.fosin.anan.cloudresource.grpc.service.inter;

import top.fosin.anan.cloudresource.grpc.permission.PermissionResp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface PermissionGrpcService {
    List<PermissionResp> findByServiceCode(@NotBlank String serviceCode);
    List<PermissionResp> findByServiceCodes(@NotEmpty List<String> serviceCodes);
}
