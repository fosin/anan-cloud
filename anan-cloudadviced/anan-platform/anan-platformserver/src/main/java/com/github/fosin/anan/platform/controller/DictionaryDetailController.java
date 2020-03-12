package com.github.fosin.anan.platform.controller;

import com.github.fosin.anan.core.exception.AnanControllerException;
import com.github.fosin.anan.mvc.constant.MvcConstant;
import com.github.fosin.anan.mvc.controller.ISimpleController;
import com.github.fosin.anan.mvc.module.PageModule;
import com.github.fosin.anan.mvc.result.ListResult;
import com.github.fosin.anan.mvc.result.ResultUtils;
import com.github.fosin.anan.mvc.service.ISimpleService;
import com.github.fosin.anan.pojo.dto.request.AnanDictionaryDetailCreateDto;
import com.github.fosin.anan.pojo.dto.request.AnanDictionaryDetailRetrieveDto;
import com.github.fosin.anan.pojo.dto.request.AnanDictionaryDetailUpdateDto;
import com.github.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import com.github.fosin.anan.platform.service.inter.DictionaryDetailService;
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
public class DictionaryDetailController implements ISimpleController<AnanDictionaryDetailEntity, Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> {
    private final DictionaryDetailService dictionaryDetailService;

    public DictionaryDetailController(DictionaryDetailService dictionaryDetailService) {
        this.dictionaryDetailService = dictionaryDetailService;
    }

    @Override
    public ISimpleService<AnanDictionaryDetailEntity, Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> getService() {
        return dictionaryDetailService;
    }

    @ApiOperation("根据字典代码获取对应的字典明细并分页排序")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageModule", value = "分页排序实体类"),
            @ApiImplicitParam(name = "dictionaryId", value = "字典代码,取值于AnanDictionaryEntity.id"),
    })
    @RequestMapping(value = MvcConstant.PATH_PAGE_LIST + "/{dictionaryId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<ListResult<AnanDictionaryDetailEntity>> pageList(@RequestBody PageModule pageModule, @PathVariable Long dictionaryId) {
        PageRequest pageRequest = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        //分页查找
        Page<AnanDictionaryDetailEntity> page;
        page = dictionaryDetailService.findAll(pageModule.getSearchText(), pageRequest, dictionaryId);

        return ResponseEntity.ok(ResultUtils.success(page.getTotalElements(), page.getContent()));
    }

    @ApiOperation("根据字典代码获取对应的字典明细")
    @RequestMapping(value = "/byCode/{dictionaryId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParam(name = "dictionaryId", value = "字典代码,取值于AnanDictionaryEntity.id")
    public ResponseEntity<List<AnanDictionaryDetailEntity>> getdictionariesByDictionaryId(@PathVariable Long dictionaryId) throws AnanControllerException {
        return ResponseEntity.ok(dictionaryDetailService.findByDictionaryId(dictionaryId));
    }

}
