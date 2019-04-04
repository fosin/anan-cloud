package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.constant.ServiceConstant;
import com.github.fosin.cdp.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterRetrieveDto;
import com.github.fosin.cdp.platformapi.service.FeignParameterFallbackServiceImpl;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(name = ServiceConstant.CDP_PLATFORMSERVER, path = UrlPrefixConstant.PARAMETER, fallback = FeignParameterFallbackServiceImpl.class)
public interface IFeignParameterService {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CdpParameterEntity> create(@RequestBody CdpParameterCreateDto entity);

    @PutMapping
    ResponseEntity<CdpParameterEntity> update(@RequestBody CdpParameterUpdateDto entity);

    @PostMapping("/entity")
    ResponseEntity<CdpParameterEntity> getParameter(@RequestParam("type") Integer type,
                                                    @RequestParam("scope") String scope, @RequestParam("name") String name);
    @PostMapping("/value")
    ResponseEntity<String> getOrCreateParameter(@RequestBody CdpParameterRetrieveDto retrieveDto);

    @PostMapping("/apply/{id}")
    ResponseEntity<Boolean> apply(@PathVariable("id") Long id);

    @PostMapping("/applys")
    ResponseEntity<Boolean> applys();
}
