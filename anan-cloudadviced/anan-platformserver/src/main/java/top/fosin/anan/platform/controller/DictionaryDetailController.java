package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.platform.dto.req.AnanDictionaryDetailCreateDto;
import top.fosin.anan.cloudresource.dto.req.AnanDictionaryDetailRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanDictionaryDetailUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanDictionaryDetailRespDto;
import top.fosin.anan.core.exception.AnanControllerException;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.service.inter.DictionaryDetailService;

import java.util.List;

/**
 * 字典明细控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.DICTIONARY_DETAIL)
@Api(value = UrlPrefixConstant.DICTIONARY_DETAIL, tags = "通用字典明细管理(增删改查)")
public class DictionaryDetailController implements ISimpleController<AnanDictionaryDetailRespDto, Long, AnanDictionaryDetailCreateDto, AnanDictionaryDetailRetrieveDto, AnanDictionaryDetailUpdateDto> {
    private final DictionaryDetailService dictionaryDetailService;

    public DictionaryDetailController(DictionaryDetailService dictionaryDetailService) {
        this.dictionaryDetailService = dictionaryDetailService;
    }

    @Override
    public DictionaryDetailService getService() {
        return dictionaryDetailService;
    }

    @ApiOperation("根据字典代码获取对应的字典明细")
    @RequestMapping(value = "/byCode/{dictionaryId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParam(name = "dictionaryId", value = "字典代码,取值于AnanDictionaryEntity.id",
            required = true, dataTypeClass = Long.class, paramType = "path")
    public ResponseEntity<List<AnanDictionaryDetailRespDto>> getdictionariesByDictionaryId(@PathVariable Long dictionaryId) throws AnanControllerException {
        return ResponseEntity.ok(dictionaryDetailService.findByDictionaryId(dictionaryId));
    }

}
