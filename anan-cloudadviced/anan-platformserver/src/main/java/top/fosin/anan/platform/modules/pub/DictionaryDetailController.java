package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.req.DictionaryDetailReqDto;
import top.fosin.anan.cloudresource.dto.res.DictionaryDetailRespDto;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.platform.modules.pub.service.inter.DictionaryDetailService;

/**
 * 字典明细控制器
 *
 * @author fosin
 */
@RestController
@RequestMapping(value = UrlPrefixConstant.DICTIONARY_DETAIL, params = UrlPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = UrlPrefixConstant.DICTIONARY_DETAIL, tags = "通用字典明细管理")
public class DictionaryDetailController implements
        ISimpleController<DictionaryDetailReqDto, DictionaryDetailRespDto, Long>,
        IRetrieveBatchController<DictionaryDetailReqDto, DictionaryDetailRespDto, Long> {
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
