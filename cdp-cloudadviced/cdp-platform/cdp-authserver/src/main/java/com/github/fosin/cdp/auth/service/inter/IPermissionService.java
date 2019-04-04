package com.github.fosin.cdp.auth.service.inter;


import com.github.fosin.cdp.jpa.service.IRetrieveOneJpaService;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IPermissionService extends IRetrieveOneJpaService<CdpPermissionEntity, Long> {
    List<CdpPermissionEntity> findByAppName(String appName);
}
