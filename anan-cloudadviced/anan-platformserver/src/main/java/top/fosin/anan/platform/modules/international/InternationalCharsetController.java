package top.fosin.anan.platform.modules.international;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.ICreateController;
import top.fosin.anan.data.controller.IDeleteController;
import top.fosin.anan.data.controller.IRetrieveController;
import top.fosin.anan.data.controller.IUpdateController;
import top.fosin.anan.data.controller.batch.IRetrieveBatchController;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetCreateDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalCharsetUpdateDTO;
import top.fosin.anan.platform.modules.international.query.InternationalCharsetQuery;
import top.fosin.anan.platform.modules.international.service.inter.InternationalCharsetService;
import top.fosin.anan.platform.modules.international.vo.InternationalCharsetListVO;
import top.fosin.anan.platform.modules.international.vo.InternationalCharsetPageVO;
import top.fosin.anan.platform.modules.international.vo.InternationalCharsetVO;

/**
 * 国际化语言字符集控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(value = PathPrefixConstant.INTERNATIONAL_CHARSET, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.INTERNATIONAL_CHARSET, tags = "国际化语言字符集管理")
public class InternationalCharsetController implements ICreateController<InternationalCharsetCreateDTO, Long>,
        IRetrieveController<InternationalCharsetQuery, InternationalCharsetVO, InternationalCharsetListVO, InternationalCharsetPageVO, Long>,
        IUpdateController<InternationalCharsetUpdateDTO, Long>,
        IRetrieveBatchController<InternationalCharsetListVO,Long>,
        IDeleteController<Long> {
    private final InternationalCharsetService internationalCharsetService;

    public InternationalCharsetController(InternationalCharsetService internationalCharsetService) {
        this.internationalCharsetService = internationalCharsetService;
    }

    @Override
    public InternationalCharsetService getService() {
        return internationalCharsetService;
    }

    @Override
    public InternationalCharsetService getBatchService() {
        return internationalCharsetService;
    }
}
