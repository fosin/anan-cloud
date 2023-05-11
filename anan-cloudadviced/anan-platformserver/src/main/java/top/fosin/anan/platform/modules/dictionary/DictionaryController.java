package top.fosin.anan.platform.modules.dictionary;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.platform.modules.dictionary.dto.DictionaryCreateDTO;
import top.fosin.anan.platform.modules.dictionary.dto.DictionaryUpdateDTO;
import top.fosin.anan.platform.modules.dictionary.query.DictionaryQuery;
import top.fosin.anan.platform.modules.dictionary.service.inter.DictionaryService;
import top.fosin.anan.platform.modules.dictionary.vo.DictionaryListVO;
import top.fosin.anan.platform.modules.dictionary.vo.DictionaryPageVO;
import top.fosin.anan.platform.modules.dictionary.vo.DictionaryVO;

/**
 * 字典控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.DICTIONARY, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.DICTIONARY, tags = "通用字典管理")
public class DictionaryController
        implements ICreateController<DictionaryCreateDTO, Long>,
        IUpdateController<DictionaryUpdateDTO, Long>,
        IDeleteController<Long>,
        IRetrieveController<DictionaryQuery, DictionaryVO, DictionaryListVO, DictionaryPageVO, Long> {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }
}
