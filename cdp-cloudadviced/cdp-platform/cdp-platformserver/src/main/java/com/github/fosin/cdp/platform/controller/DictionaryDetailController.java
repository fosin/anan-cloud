package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.mvc.constant.MvcConstant;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.ListResult;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysDictionaryDetailUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysDictionaryDetailEntity;
import com.github.fosin.cdp.platformapi.service.inter.IDictionaryDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description 字典明细控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping("v1/dictionaryDetail")
@Api(value = "v1/dictionaryDetail", tags = "通用字典明细管理", description = "通用字典明细管理(增删改查)")
public class DictionaryDetailController implements ISimpleController<CdpSysDictionaryDetailEntity, Long, CdpSysDictionaryDetailCreateDto, CdpSysDictionaryDetailRetrieveDto, CdpSysDictionaryDetailUpdateDto> {
    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

    @Override
    public ISimpleService<CdpSysDictionaryDetailEntity, Long, CdpSysDictionaryDetailCreateDto, CdpSysDictionaryDetailRetrieveDto, CdpSysDictionaryDetailUpdateDto> getService() {
        return dictionaryDetailService;
    }

    @ApiOperation("根据字典代码获取对应的字典明细并分页排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageModule", value = "分页排序实体类"),
            @ApiImplicitParam(name = "code", value = "字典代码,取值于CdpSysDictionaryEntity.code"),
    })
    @RequestMapping(value = MvcConstant.PATH_PAGE_LIST + "/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<ListResult<CdpSysDictionaryDetailEntity>> pageList(@RequestBody PageModule pageModule, @PathVariable Long code) {
        PageRequest pageRequest = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        //分页查找
        Page<CdpSysDictionaryDetailEntity> page;
        page = dictionaryDetailService.findAll(pageModule.getSearchText(), pageRequest, code);

        return ResponseEntity.ok(ResultUtils.success(page.getTotalElements(), page.getContent()));
    }

    @ApiOperation("根据字典代码获取对应的字典明细")
    @RequestMapping(value = "/byCode/{code}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParam(name = "code", value = "字典代码,取值于CdpSysDictionaryEntity.code")
    public ResponseEntity<List<CdpSysDictionaryDetailEntity>> getdictionariesByCode(@PathVariable Long code) throws CdpControllerException {
        List<CdpSysDictionaryDetailEntity> entities = dictionaryDetailService.findByCode(code);
        return ResponseEntity.ok(entities);
    }

}
