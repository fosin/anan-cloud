package com.github.fosin.anan.auth.service.inter;


import com.github.fosin.anan.jpa.service.IRetrieveOneJpaService;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;
import com.github.fosin.anan.platformapi.dto.request.AnanPermissionCreateDto;
import com.github.fosin.anan.platformapi.dto.request.AnanPermissionRetrieveDto;
import com.github.fosin.anan.platformapi.dto.request.AnanPermissionUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface PermissionService extends IRetrieveOneJpaService<AnanPermissionEntity, Long> {
    List<AnanPermissionEntity> findByAppName(String appName);
}
