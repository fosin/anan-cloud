package top.fosin.anan.cloudresource.service.inter.base;

import top.fosin.anan.cloudresource.entity.res.PermissionDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
