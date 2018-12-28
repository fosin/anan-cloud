package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysParameterEntity;
import com.github.fosin.cdp.platformapi.parameter.OrganizParameterUtil;
import com.github.fosin.cdp.platformapi.parameter.ParameterUtil;
import com.github.fosin.cdp.platformapi.parameter.UserParameterUtil;
import com.github.fosin.cdp.platformapi.service.inter.IParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description 参数控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/parameter")
@Api(value = "v1/parameter", tags = "通用参数管理", description = "通用参数管理相关操作(参数获取、自动创建)")
public class ParameterController implements ISimpleController<CdpSysParameterEntity, Long, CdpSysParameterEntity, CdpSysParameterEntity, CdpSysParameterEntity> {
    @Autowired
    private IParameterService parameterService;

    @ApiOperation(value = "获取指定机构或指定用户的参数整条数据", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "参数类型,取值于CdpSysParameterEntity.type"),
            @ApiImplicitParam(name = "scope", value = "作用域，一般指机构ID或用户ID，也可能是自定义扩展的数据,取值于CdpSysParameterEntity.scope"),
            @ApiImplicitParam(name = "name", value = "参数键,取值于CdpSysParameterEntity.name"),
    })
    @RequestMapping(value = "/{type}/{scope}/{name}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> getByTypeAndscopeAndName(@PathVariable("type") Integer type,
                                                           @PathVariable("scope") String scope, @PathVariable("name") String name) {
        return ResponseEntity.ok(ParameterUtil.getParameter(type, scope, name));
    }

    @ApiOperation(value = "获取当前机构或用户的参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "参数类型,取值于CdpSysParameterEntity.type"),
            @ApiImplicitParam(name = "name", value = "参数键,取值于CdpSysParameterEntity.name"),
    })
    @RequestMapping(value = "/value/{type}/{name}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> getValueByTypeAndName(@PathVariable("type") Integer type, @PathVariable("name") String name) {
        String value = null;
        if (type.equals(OrganizParameterUtil.TYPE)) {
            value = OrganizParameterUtil.getNearestParameter(name);
        }
        if (type.equals(UserParameterUtil.TYPE)) {
            value = UserParameterUtil.getNearestParameter(name);
        }
        return ResponseEntity.ok(value);
    }

    @ApiOperation(value = "获取或创建当前机构或用户参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数，如果缓存和数据库中都没有找到参数，则自动创建一个无域参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "参数类型,取值于CdpSysParameterEntity.type"),
            @ApiImplicitParam(name = "name", value = "参数键,取值于CdpSysParameterEntity.name"),
            @ApiImplicitParam(name = "defaultValue", value = "默认值,取值于CdpSysParameterEntity.defaultValue"),
            @ApiImplicitParam(name = "description", value = "参数描述,取值于CdpSysParameterEntity.description"),
    })
    @RequestMapping(value = "/value/{type}/{name}/{defaultValue}/{description}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> getOrCreateValueByTypeAndscopeAndName(@PathVariable("type") Integer type,
                                                                        @PathVariable("name") String name,
                                                                        @PathVariable("defaultValue") String defaultValue,
                                                                        @PathVariable("description") String description
    ) {
        String value = null;
        if (type.equals(OrganizParameterUtil.TYPE)) {
            value = OrganizParameterUtil.getOrCreateParameter(name, defaultValue, description);
        }
        if (type.equals(UserParameterUtil.TYPE)) {
            value = UserParameterUtil.getOrCreateParameter(name, defaultValue, description);
        }
        return ResponseEntity.ok(value);
    }

    @ApiOperation(value = "获取指定机构或指定用户参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数,如果缓存和数据库中都没有找到参数，返回null值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "参数类型,取值于CdpSysParameterEntity.type"),
            @ApiImplicitParam(name = "scope", value = "作用域，一般指机构ID或用户ID，也可能是自定义扩展的数据,取值于CdpSysParameterEntity.scope"),
            @ApiImplicitParam(name = "name", value = "参数键,取值于CdpSysParameterEntity.name"),
    })
    @RequestMapping(value = "/value/{type}/{scope}/{name}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> getValueByTypeAndscopeAndName(@PathVariable("type") Integer type, @PathVariable("scope") String scope, @PathVariable("name") String name) {
        String value = null;
        if (type.equals(OrganizParameterUtil.TYPE)) {
            value = OrganizParameterUtil.getNearestParameter(scope, name);
        }
        if (type.equals(UserParameterUtil.TYPE)) {
            value = UserParameterUtil.getNearestParameter(scope, name);
        }
        return ResponseEntity.ok(value);
    }

    @ApiOperation(value = "获取或创建指定机构或指定用户参数值", notes = "type=1则是机构参数(机构参数系统会从当前机构向逐级上级机构查找该参数),type=2则是用户参数，如果缓存和数据库中都没有找到参数，则自动创建一个无域参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "参数类型,取值于CdpSysParameterEntity.type"),
            @ApiImplicitParam(name = "scope", value = "作用域，一般指机构ID或用户ID，也可能是自定义扩展的数据,取值于CdpSysParameterEntity.scope"),
            @ApiImplicitParam(name = "name", value = "参数键,取值于CdpSysParameterEntity.name"),
            @ApiImplicitParam(name = "defaultValue", value = "默认值,取值于CdpSysParameterEntity.defaultValue"),
            @ApiImplicitParam(name = "description", value = "参数描述,取值于CdpSysParameterEntity.description"),
    })
    @RequestMapping(value = "/value/{type}/{scope}/{name}/{defaultValue}/{description}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> getOrCreateValueByTypeAndscopeAndName(@PathVariable("type") Integer type,
                                                                        @PathVariable("scope") String scope,
                                                                        @PathVariable("name") String name,
                                                                        @PathVariable("defaultValue") String defaultValue,
                                                                        @PathVariable("description") String description
    ) {
        String value = null;
        if (type.equals(OrganizParameterUtil.TYPE)) {
            value = OrganizParameterUtil.getOrCreateParameter(scope, name, defaultValue, description);
        }
        if (type.equals(UserParameterUtil.TYPE)) {
            value = UserParameterUtil.getOrCreateParameter(scope, name, defaultValue, description);
        }
        return ResponseEntity.ok(value);
    }

    @ApiOperation(value = "根据参数ID刷新参数缓存信息", notes = "该方法是幂等性的，可以重复调用")
    @ApiImplicitParam(name = "id", value = "参数ID,取值于CdpSysParameterEntity.id")
    @RequestMapping(value = "/apply/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> apply(@PathVariable("id") Long id) throws CdpServiceException {
        return ResponseEntity.ok(parameterService.applyChange(id));
    }

    @ApiOperation(value = "刷新所有已更改参数缓存信息", notes = "该方法只能在有修改参数信息的情况下使用，这是一个批量刷新参数缓存的操作")
    @RequestMapping(value = "/applys", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object> applys() throws CdpServiceException {
        return ResponseEntity.ok(parameterService.applyChanges());
    }

    @Override
    public ISimpleService<CdpSysParameterEntity, Long, CdpSysParameterEntity, CdpSysParameterEntity, CdpSysParameterEntity> getService() {
        return parameterService;
    }
}
