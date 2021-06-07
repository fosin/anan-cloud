package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.AnanParameterCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterRetrieveDto;
import top.fosin.anan.cloudresource.dto.req.AnanParameterUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.dto.TreeDto;
import top.fosin.anan.platform.service.inter.ParameterService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;

/**
 * 参数控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.PARAMETER)
@Api(value = UrlPrefixConstant.PARAMETER, tags = "通用参数管理相关操作(参数获取、自动创建)")
public class ParameterController implements ISimpleController<AnanParameterRespDto, Long,
        AnanParameterCreateDto, AnanParameterRetrieveDto, AnanParameterUpdateDto> {
    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @ApiOperation(value = "获取指定机构或指定用户的参数整条数据", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @RequestMapping(value = ParameterFeignService.PATH_NEAREST, method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "字典类型,取值于AnanParameterEntity.type",
                    required = true, dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "字典作用域,取值于AnanParameterEntity.scope",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "字典名称,取值于AnanParameterEntity.name",
                    required = true, dataTypeClass = String.class, paramType = "query")
    })
    public ResponseEntity<AnanParameterRespDto> getNearestParameter(@Min(0) @RequestParam("type") Integer type,
                                                                    @RequestParam("scope") String scope,
                                                                    @NotBlank @RequestParam("name") String name) {
        return ResponseEntity.ok(parameterService.getNearestParameter(type, scope, name));
    }

    @ApiOperation(value = "获取指定机构或指定用户的参数整条数据", notes = "type=1则是机构参数,只找当前机构,type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @RequestMapping(value = ParameterFeignService.PATH_DTO, method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "字典类型,取值于AnanParameterEntity.type",
                    required = true, dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "字典作用域,取值于AnanParameterEntity.scope",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "字典名称,取值于AnanParameterEntity.name",
                    required = true, dataTypeClass = String.class, paramType = "query")
    })
    public ResponseEntity<AnanParameterRespDto> getParameter(@Min(0) @RequestParam("type") Integer type,
                                                             @RequestParam("scope") String scope,
                                                             @NotBlank @RequestParam("name") String name) {
        return ResponseEntity.ok(parameterService.getParameter(type, scope, name));
    }

    @ApiOperation(value = "获取或创建指定机构或指定用户参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数，如果缓存和数据库中都没有找到参数，则自动创建一个无域参数")
    @RequestMapping(value = ParameterFeignService.PATH_VALUE, method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParam(name = TreeDto.ID_NAME, value = "参数ID,取值于AnanParameterEntity.id",
            required = true, dataTypeClass = AnanParameterRetrieveDto.class, paramType = "body")
    public ResponseEntity<String> getOrCreateParameter(@RequestBody AnanParameterRetrieveDto retrieveDto) {
        int type = retrieveDto.getType();
        String scope = retrieveDto.getScope();
        String name = retrieveDto.getName();
        String defaultValue = retrieveDto.getDefaultValue();
        String description = retrieveDto.getDescription();
        return ResponseEntity.ok(parameterService.getOrCreateParameter(type, scope, name, defaultValue, description).getValue());
    }

    @ApiOperation(value = "根据参数ID刷新参数缓存信息", notes = "该方法是幂等性的，可以重复调用")
    @ApiImplicitParam(name = TreeDto.ID_NAME, value = "参数ID,取值于AnanParameterEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @RequestMapping(value = ParameterFeignService.PATH_APPLY_ID, method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Boolean> applyChange(@Min(1) @PathVariable(TreeDto.ID_NAME) Long id) {
        return ResponseEntity.ok(parameterService.applyChange(id));
    }

    @ApiOperation(value = "刷新所有已更改参数缓存信息", notes = "该方法只能在有修改参数信息的情况下使用，这是一个批量刷新参数缓存的操作")
    @PostMapping(value = ParameterFeignService.PATH_APPLYS)
    public ResponseEntity<Boolean> applyChangeAll() {
        return ResponseEntity.ok(parameterService.applyChangeAll());
    }

    @ApiOperation(value = "刷新指定参数缓存信息", notes = "这是一个批量刷新参数缓存的操作")
    @PostMapping(value = ParameterFeignService.PATH_APPLYS_IDS)
    public ResponseEntity<Boolean> applyChanges(@NotEmpty @RequestBody List<Long> ids) {
        return ResponseEntity.ok(parameterService.applyChanges(ids));
    }

    @PostMapping(value = ParameterFeignService.PATH_CANCELDELETE)
    @ApiOperation(value = "取消删除参数")
    @ApiImplicitParam(name = "ids", value = "主键编号集合",
            paramType = "body", required = true, dataTypeClass = Collection.class)
    public void cancelDelete(@NotEmpty @RequestBody Collection<Long> ids) {
        getService().cancelDelete(ids);
    }

    @Override
    public ParameterService getService() {
        return parameterService;
    }
}
