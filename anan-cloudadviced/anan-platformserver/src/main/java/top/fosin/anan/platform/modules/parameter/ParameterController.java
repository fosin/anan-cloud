package top.fosin.anan.platform.modules.parameter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.ParameterCreateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.service.inter.feign.ParameterFeignService;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.data.entity.res.TreeVO;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
import top.fosin.anan.data.valid.group.SingleQuery;
import top.fosin.anan.platform.modules.parameter.query.ParameterQuery;
import top.fosin.anan.platform.modules.parameter.service.inter.ParameterService;
import top.fosin.anan.platform.modules.parameter.vo.ParameterListVO;
import top.fosin.anan.platform.modules.parameter.vo.ParameterPageVO;
import top.fosin.anan.platform.modules.parameter.vo.ParameterVO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.List;

/**
 * 参数控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.PARAMETER)
@Api(value = PathPrefixConstant.PARAMETER, tags = "通用参数管理(参数获取、自动创建)")
@AllArgsConstructor
public class ParameterController implements
        ICreateController<ParameterCreateDTO, Long>,
        IRetrieveController<ParameterQuery, ParameterVO, ParameterListVO, ParameterPageVO, Long>,
        IUpdateController<ParameterUpdateDTO, Long>,
        IDeleteController<Long> {

    private final ParameterService parameterService;

    @ApiOperation(value = "获取指定机构或指定用户的参数整条数据", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @GetMapping(value = ParameterFeignService.PATH_NEAREST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "字典类型,取值于Parameter.type",
                    required = true, dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "字典作用域,取值于Parameter.scope",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "字典名称,取值于Parameter.name",
                    required = true, dataTypeClass = String.class, paramType = "query")
    })
    public SingleResult<ParameterDTO> getNearestParameter(
            @PositiveOrZero @RequestParam("type") Integer type,
            @RequestParam("scope") String scope,
            @NotBlank @RequestParam("name") String name) {
        return ResultUtils.success(parameterService.getNearestParameter(type, scope, name));
    }

    @ApiOperation(value = "获取指定机构或指定用户的参数整条数据", notes = "type=1则是机构参数,只找当前机构,type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @GetMapping(value = ParameterFeignService.PATH_3ARGS)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "字典类型,取值于Parameter.type",
                    required = true, dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", value = "字典作用域,取值于Parameter.scope",
                    required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "字典名称,取值于Parameter.name",
                    required = true, dataTypeClass = String.class, paramType = "query")
    })
    public SingleResult<ParameterDTO> getParameter(
            @PositiveOrZero @RequestParam("type") Integer type,
            @RequestParam("scope") String scope,
            @NotBlank @RequestParam("name") String name) {
        return ResultUtils.success(parameterService.getParameter(type, scope, name));
    }

    @ApiOperation(value = "获取或创建指定机构或指定用户参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数，如果缓存和数据库中都没有找到参数，则自动创建一个无域参数")
    @RequestMapping(value = ParameterFeignService.PATH_VALUE, method = {RequestMethod.POST, RequestMethod.GET})
    @ApiImplicitParam(name = TreeVO.ID_NAME, value = "参数ID,取值于Parameter.id",
            required = true, dataTypeClass = ParameterUpdateDTO.class, paramType = "body")
    public SingleResult<String> getOrCreateParameter(
            @Validated({SingleQuery.class}) @RequestBody ParameterUpdateDTO reqDto) {
        int type = reqDto.getType();
        String scope = reqDto.getScope();
        String name = reqDto.getName();
        String defaultValue = reqDto.getDefaultValue();
        String description = reqDto.getDescription();
        return ResultUtils.success(parameterService.getOrCreateParameter(type, scope, name, defaultValue, description));
    }

    @ApiOperation(value = "根据参数ID刷新参数缓存信息", notes = "该方法是幂等性的，可以重复调用")
    @ApiImplicitParam(name = TreeVO.ID_NAME, value = "参数ID,取值于Parameter.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    @GetMapping(value = ParameterFeignService.PATH_APPLY_ID)
    public SingleResult<Boolean> applyChange(@Positive @PathVariable(TreeVO.ID_NAME) Long id) {
        return ResultUtils.success(parameterService.applyChange(id));
    }

    @ApiOperation(value = "刷新所有已更改参数缓存信息", notes = "该方法只能在有修改参数信息的情况下使用，这是一个批量刷新参数缓存的操作")
    @GetMapping(value = ParameterFeignService.PATH_APPLYS)
    public SingleResult<Boolean> applyChangeAll() {
        return ResultUtils.success(parameterService.applyChangeAll());
    }

    @ApiOperation(value = "刷新指定参数缓存信息", notes = "这是一个批量刷新参数缓存的操作")
    @PostMapping(value = ParameterFeignService.PATH_APPLYS_IDS)
    public SingleResult<Boolean> applyChanges(@NotEmpty @RequestBody List<Long> ids) {
        return ResultUtils.success(parameterService.applyChanges(ids));
    }

    @PostMapping(value = ParameterFeignService.PATH_CANCELDELETE)
    @ApiOperation(value = "取消删除参数")
    @ApiImplicitParam(name = "ids", value = "主键编号集合",
            paramType = "body", required = true, dataTypeClass = Collection.class)
    public void cancelDelete(@NotEmpty @RequestBody List<Long> ids) {
        getService().cancelDelete(ids);
    }

    @Override
    public ParameterService getService() {
        return parameterService;
    }
}
