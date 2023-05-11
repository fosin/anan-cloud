package top.fosin.anan.platform.modules.dictionary;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.req.DictionaryDetailReqDTO;
import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.platform.modules.dictionary.service.inter.DictionaryDetailService;

/**
 * 字典明细控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = PathPrefixConstant.DICTIONARY_DETAIL, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.DICTIONARY_DETAIL, tags = "通用字典明细管理")
public class DictionaryDetailController implements
        ISimpleController<DictionaryDetailReqDTO, DictionaryDetailRespDTO, Long>,
        IRetrieveBatchController<DictionaryDetailRespDTO, Long> {
    private final DictionaryDetailService dictionaryDetailService;

    public DictionaryDetailController(DictionaryDetailService dictionaryDetailService) {
        this.dictionaryDetailService = dictionaryDetailService;
    }

    @Override
    public DictionaryDetailService getService() {
        return dictionaryDetailService;
    }

    @Override
    public DictionaryDetailService getBatchService() {
        return dictionaryDetailService;
    }
}
