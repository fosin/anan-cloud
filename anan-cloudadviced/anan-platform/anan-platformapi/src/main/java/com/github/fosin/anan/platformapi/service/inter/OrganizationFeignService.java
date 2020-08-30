package com.github.fosin.anan.platformapi.service.inter;


import com.github.fosin.anan.pojo.constant.ServiceConstant;
import com.github.fosin.anan.pojo.constant.UrlPrefixConstant;
import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.platformapi.service.ParameterFeignFallbackServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.ORGANIZATION, fallback = ParameterFeignFallbackServiceImpl.class)
public interface OrganizationFeignService {
    @PostMapping({"/{id}"})
    @ApiOperation("根据主键ID查询一条数据")
    ResponseEntity<AnanOrganizationEntity> findOne(@PathVariable("id") Long id);

    @PostMapping("/listChild/{pid}")
    ResponseEntity<List<AnanOrganizationEntity>> listChild(@PathVariable("pid") Long pid);

    @PostMapping("/listAllChild/{pid}")
    ResponseEntity<List<AnanOrganizationEntity>> listAllChild(@PathVariable("pid") Long pid);

    @PostMapping("/tree/{topId}")
    ResponseEntity<List<AnanOrganizationEntity>> tree(@PathVariable("topId") Long topId);

}

