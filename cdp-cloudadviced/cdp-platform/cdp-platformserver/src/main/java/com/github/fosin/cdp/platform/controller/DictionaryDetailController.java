package com.github.fosin.cdp.platform.controller;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.mvc.constant.MvcConstant;
import com.github.fosin.cdp.mvc.controller.ISimpleController;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.ListResult;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpDictionaryDetailUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpDictionaryDetailEntity;
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
public class DictionaryDetailController implements ISimpleController<CdpDictionaryDetailEntity, Long, CdpDictionaryDetailCreateDto, CdpDictionaryDetailRetrieveDto, CdpDictionaryDetailUpdateDto> {
    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

    @Override
    public ISimpleService<CdpDictionaryDetailEntity, Long, CdpDictionaryDetailCreateDto, CdpDictionaryDetailRetrieveDto, CdpDictionaryDetailUpdateDto> getService() {
        return dictionaryDetailService;
    }

    @ApiOperation("根据字典代码获取对应的字典明细并分页排序")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageModule", value = "分页排序实体类"),
            @ApiImplicitParam(name = "dictionaryId", value = "字典代码,取值于CdpDictionaryEntity.id"),
    })
    @RequestMapping(value = MvcConstant.PATH_PAGE_LIST + "/{dictionaryId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<ListResult<CdpDictionaryDetailEntity>> pageList(@RequestBody PageModule pageModule, @PathVariable Long dictionaryId) {
        PageRequest pageRequest = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        //分页查找
        Page<CdpDictionaryDetailEntity> page;
        page = dictionaryDetailService.findAll(pageModule.getSearchText(), pageRequest, dictionaryId);

        return ResponseEntity.ok(ResultUtils.success(page.getTotalElements(), page.getContent()));
    }

    @ApiOperation("根据字典代码获取对应的字典明细")
    @RequestMapping(value = "/byCode/{dictionaryId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParam(name = "dictionaryId", value = "字典代码,取值于CdpDictionaryEntity.id")
    public ResponseEntity<List<CdpDictionaryDetailEntity>> getdictionariesByDictionaryId(@PathVariable Long dictionaryId) throws CdpControllerException {
        return ResponseEntity.ok(dictionaryDetailService.findByDictionaryId(dictionaryId));
    }

}
