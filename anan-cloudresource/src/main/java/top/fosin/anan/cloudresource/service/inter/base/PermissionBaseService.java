package top.fosin.anan.cloudresource.service.inter.base;

import top.fosin.anan.cloudresource.entity.res.PermissionDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
public interface PermissionBaseService {
    List<PermissionDTO> findByServiceCode(@NotBlank String serviceCode);
    List<PermissionDTO> findByServiceCodes(@NotEmpty List<String> serviceCodes);
}
