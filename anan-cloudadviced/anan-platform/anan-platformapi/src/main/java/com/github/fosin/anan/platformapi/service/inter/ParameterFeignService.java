package com.github.fosin.anan.platformapi.service.inter;

import com.github.fosin.anan.cloudresource.constant.ServiceConstant;
import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.cloudresource.dto.request.AnanParameterRetrieveDto;
import com.github.fosin.anan.platformapi.service.ParameterFeignFallbackServiceImpl;
import com.github.fosin.anan.cloudresource.dto.request.AnanParameterCreateDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanParameterUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanParameterEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(name = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.PARAMETER, fallback = ParameterFeignFallbackServiceImpl.class)
public interface ParameterFeignService {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AnanParameterEntity> create(@RequestBody AnanParameterCreateDto entity);

    @PutMapping
    ResponseEntity<AnanParameterEntity> update(@RequestBody AnanParameterUpdateDto entity);

    @PostMapping("/entity")
    ResponseEntity<AnanParameterEntity> getParameter(@RequestParam("type") Integer type,
                                                     @RequestParam("scope") String scope,
                                                     @RequestParam("name") String name);

    @PostMapping(value = "/entity/nearest")
    ResponseEntity<AnanParameterEntity> getNearestParameter(@RequestParam("type") Integer type,
                                                            @RequestParam("scope") String scope,
                                                            @RequestParam("name") String name);

    @PostMapping("/value")
    ResponseEntity<String> getOrCreateParameter(@RequestBody AnanParameterRetrieveDto retrieveDto);

    @PostMapping("/apply/{id}")
    ResponseEntity<Boolean> apply(@PathVariable("id") Long id);

    @PostMapping("/applys")
    ResponseEntity<Boolean> applys();
}
