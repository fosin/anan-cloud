package top.fosin.anan.cloudresource.service.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterRetrieveDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.cloudresource.service.ParameterFeignFallbackServiceImpl;
import top.fosin.anan.model.dto.TreeDto;

/**
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(name = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.PARAMETER, fallback = ParameterFeignFallbackServiceImpl.class)
public interface ParameterFeignService {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AnanParameterRespDto> create(@RequestBody AnanParameterCreateDto entity);

    @PutMapping
    ResponseEntity<AnanParameterRespDto> update(@RequestBody AnanParameterUpdateDto entity);

    @PostMapping("/entity")
    ResponseEntity<AnanParameterRespDto> getParameter(@RequestParam("type") Integer type,
                                                      @RequestParam("scope") String scope,
                                                      @RequestParam("name") String name);

    @PostMapping(value = "/entity/nearest")
    ResponseEntity<AnanParameterRespDto> getNearestParameter(@RequestParam("type") Integer type,
                                                             @RequestParam("scope") String scope,
                                                             @RequestParam("name") String name);

    @PostMapping("/value")
    ResponseEntity<String> getOrCreateParameter(@RequestBody AnanParameterRetrieveDto retrieveDto);

    @PostMapping("/apply/{id}")
    ResponseEntity<Boolean> apply(@PathVariable(TreeDto.ID_NAME) Long id);

    @PostMapping("/applys")
    ResponseEntity<Boolean> applys();
}
