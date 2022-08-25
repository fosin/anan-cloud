package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.DictionaryReqDto;
import top.fosin.anan.cloudresource.dto.res.DictionaryRespDto;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.pub.service.inter.DictionaryService;

/**
 * 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = UrlPrefixConstant.DICTIONARY, params = UrlPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = UrlPrefixConstant.DICTIONARY, tags = "通用字典管理")
public class DictionaryController implements ISimpleController<DictionaryReqDto, DictionaryRespDto, Long> {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }
}
