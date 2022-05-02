package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.AnanDictionaryReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanDictionaryRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.service.inter.DictionaryService;

/**
 * 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.DICTIONARY)
@Api(value = UrlPrefixConstant.DICTIONARY, tags = "通用字典管理")
public class DictionaryController implements ISimpleController<AnanDictionaryRespDto,
        Long, AnanDictionaryReqDto, AnanDictionaryReqDto, AnanDictionaryReqDto> {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }
}
