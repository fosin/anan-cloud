package top.fosin.anan.platform.controller;

import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanDictionaryDetailUpdateDto;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.module.PageModule;
import top.fosin.anan.model.result.ListResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.model.service.ISimpleService;
import top.fosin.anan.platform.service.inter.DictionaryDetailService;
import top.fosin.anan.platformapi.entity.AnanDictionaryDetailEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(UrlPrefixConstant.DICTIONARY_DETAIL)
@Api(value = UrlPrefixConstant.DICTIONARY_DETAIL, tags = "通用字典明细管理(增删改查)")
public class DictionaryDetailController implements ISimpleController<AnanDictionaryDetailEntity, Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> {
    private final DictionaryDetailService dictionaryDetailService;

    public DictionaryDetailController(DictionaryDetailService dictionaryDetailService) {
        this.dictionaryDetailService = dictionaryDetailService;
    }

    @Override
    public ISimpleService<AnanDictionaryDetailEntity, Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> getService() {
        return dictionaryDetailService;
    }

    @ApiOperation("根据字典代码获取对应的字典明细")
    @RequestMapping(value = "/byCode/{dictionaryId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParam(name = "dictionaryId", value = "字典代码,取值于AnanDictionaryEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanDictionaryDetailEntity>> getdictionariesByDictionaryId(@PathVariable Long dictionaryId) throws AnanControllerException {
        return ResponseEntity.ok(dictionaryDetailService.findByDictionaryId(dictionaryId));
    }

}
