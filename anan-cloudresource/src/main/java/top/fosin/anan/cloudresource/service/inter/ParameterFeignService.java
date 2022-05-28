package top.fosin.anan.cloudresource.service.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.service.ParameterFeignFallbackServiceImpl;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.dto.res.TreeDto;
import top.fosin.anan.model.result.SingleResult;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(name = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.PARAMETER,
        fallback = ParameterFeignFallbackServiceImpl.class,contextId = "parameterFeignService")
public interface ParameterFeignService {
    String PATH_VALUE = "value";
    String PATH_DTO = PathConstant.PATH_DTO;
    String PATH_NEAREST = "nearest";
    String PATH_APPLY_ID = "/apply" + PathConstant.PATH_ID;
    String PATH_APPLYS = "/applys";
    String PATH_APPLYS_IDS = PATH_APPLYS + PathConstant.PATH_IDS;
    String PATH_CANCELDELETE = "/cancelDelete" + PathConstant.PATH_IDS;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SingleResult<ParameterRespDto> create(@RequestBody ParameterReqDto entity);

    @PutMapping
    SingleResult<ParameterRespDto> update(@RequestBody ParameterReqDto entity);

    @PostMapping(PATH_DTO)
    SingleResult<ParameterRespDto> getParameter(@RequestParam("type") Integer type,
                                                  @RequestParam("scope") String scope,
                                                  @RequestParam("name") String name);

    @PostMapping(value = PATH_NEAREST)
    SingleResult<ParameterRespDto> getNearestParameter(@RequestParam("type") Integer type,
                                                         @RequestParam("scope") String scope,
                                                         @RequestParam("name") String name);

    @PostMapping(PATH_VALUE)
    SingleResult<String> getOrCreateParameter(@RequestBody ParameterReqDto retrieveDto);

    @PostMapping(PATH_APPLY_ID)
    SingleResult<Boolean> applyChange(@PathVariable(TreeDto.ID_NAME) Long id);

    @GetMapping(PATH_APPLYS)
    SingleResult<Boolean> applyChangeAll();

    @PostMapping(value = ParameterFeignService.PATH_APPLYS_IDS)
    SingleResult<Boolean> applyChangeAll(@NotEmpty @RequestBody List<Long> ids);

    @PostMapping(value = ParameterFeignService.PATH_CANCELDELETE)
    void cancelDelete(@NotEmpty @RequestBody Collection<Long> ids);
}
