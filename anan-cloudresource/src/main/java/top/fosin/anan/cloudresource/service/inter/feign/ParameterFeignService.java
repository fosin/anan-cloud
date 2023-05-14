package top.fosin.anan.cloudresource.service.inter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.req.ParameterCreateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.service.ParameterFeignFallbackServiceImpl;
import top.fosin.anan.data.constant.PathConstant;
import top.fosin.anan.data.entity.res.TreeVO;
import top.fosin.anan.data.result.SingleResult;

import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(name = ServiceConstant.ANAN_PLATFORMSERVER, path = PathPrefixConstant.PARAMETER,
        fallback = ParameterFeignFallbackServiceImpl.class, contextId = "parameterFeignService")
public interface ParameterFeignService {
    String PATH_VALUE = "value";
    String PATH_3ARGS = "/3args";
    String PATH_NEAREST = "nearest";
    String PATH_APPLY_ID = "/apply" + PathConstant.PATH_ID;
    String PATH_APPLYS = "/applys";
    String PATH_APPLYS_IDS = PATH_APPLYS + PathConstant.PATH_IDS;
    String PATH_CANCELDELETE = "/cancelDelete" + PathConstant.PATH_IDS;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    SingleResult<ParameterDTO> processCreate(@RequestBody ParameterCreateDTO reqDto);

    @PutMapping()
    void processUpdate(@RequestBody ParameterUpdateDTO reqDto);

    @GetMapping(value = PATH_3ARGS)
    SingleResult<ParameterDTO> getParameter(@RequestParam("type") Integer type,
                                                @RequestParam("scope") String scope,
                                                @RequestParam("name") String name);

    @GetMapping(value = PATH_NEAREST)
    SingleResult<ParameterDTO> getNearestParameter(@RequestParam("type") Integer type,
                                                       @RequestParam("scope") String scope,
                                                       @RequestParam("name") String name);

    @PostMapping(value = PATH_VALUE)
    SingleResult<String> getOrCreateParameter(@RequestBody ParameterUpdateDTO reqDto);

    @GetMapping(value = PATH_APPLY_ID)
    SingleResult<Boolean> applyChange(@PathVariable(TreeVO.ID_NAME) Long id);

    @GetMapping(value = PATH_APPLYS)
    SingleResult<Boolean> applyChangeAll();

    @PostMapping(value = ParameterFeignService.PATH_APPLYS_IDS)
    SingleResult<Boolean> applyChanges(@RequestBody List<Long> ids);

    @PostMapping(value = ParameterFeignService.PATH_CANCELDELETE)
    void cancelDelete(@RequestBody Collection<Long> ids);
}
