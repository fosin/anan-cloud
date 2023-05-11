package top.fosin.anan.platform.modules.dictionary;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.DictionaryReqDTO;
import top.fosin.anan.cloudresource.entity.res.DictionaryRespDTO;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.platform.modules.dictionary.service.inter.DictionaryService;

/**
 * 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.DICTIONARY, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.DICTIONARY, tags = "通用字典管理")
public class DictionaryController implements
        ISimpleController<DictionaryReqDTO, DictionaryRespDTO, Long> {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }
}
