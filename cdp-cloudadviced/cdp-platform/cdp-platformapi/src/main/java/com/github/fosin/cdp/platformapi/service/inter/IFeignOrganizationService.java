package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.constant.ServiceConstant;
import com.github.fosin.cdp.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.cdp.platformapi.service.FeignParameterFallbackServiceImpl;
import com.github.fosin.cdp.platformapi.dto.RegisterDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpOrganizationPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationAuthEntity;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationEntity;
import com.github.fosin.cdp.platformapi.entity.CdpOrganizationPermissionEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
@FeignClient(value = ServiceConstant.CDP_PLATFORMSERVER, path = UrlPrefixConstant.ORGANIZATION, fallback = FeignParameterFallbackServiceImpl.class)
public interface IFeignOrganizationService {
    @PostMapping({"/{id}"})
    @ApiOperation("根据主键ID查询一条数据")
    ResponseEntity<CdpOrganizationEntity> findOne(@PathVariable("id") Long id);

    @PutMapping("/permissions/{organizId}")
    ResponseEntity<List<CdpOrganizationPermissionEntity>> permissions(@PathVariable("organizId") Long organizId);

    @PutMapping("/permissions/{organizId}")
    ResponseEntity<Collection<CdpOrganizationPermissionEntity>> permissions(@RequestBody List<CdpOrganizationPermissionUpdateDto> entities,
                                                                            @PathVariable("organizId") Long organizId);

    @PostMapping("/listChild/{pId}")
    ResponseEntity<List<CdpOrganizationEntity>> listChild(@PathVariable("pId") Long pId);

    @PostMapping("/listAllChild/{pId}")
    ResponseEntity<List<CdpOrganizationEntity>> listAllChild(@PathVariable("pId") Long pId);

    @PostMapping("/tree/{topId}")
    ResponseEntity<List<CdpOrganizationEntity>> tree(@PathVariable("topId") Long topId);

    @PutMapping(value = "/register")
    ResponseEntity<Boolean> register(@RequestBody RegisterDto registerDto);

    @PostMapping("/auth/{organizId}")
    ResponseEntity<CdpOrganizationAuthEntity> getOrganizAuth(@PathVariable("organizId") Long organizId);

}

