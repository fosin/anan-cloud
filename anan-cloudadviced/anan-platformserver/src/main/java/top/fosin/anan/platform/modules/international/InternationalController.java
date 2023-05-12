package top.fosin.anan.platform.modules.international;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.controller.*;
import top.fosin.anan.platform.modules.international.dto.InternationalCreateDTO;
import top.fosin.anan.platform.modules.international.dto.InternationalUpdateDTO;
import top.fosin.anan.platform.modules.international.query.InternationalQuery;
import top.fosin.anan.platform.modules.international.service.inter.InternationalService;
import top.fosin.anan.platform.modules.international.vo.InternationalListVO;
import top.fosin.anan.platform.modules.international.vo.InternationalPageVO;
import top.fosin.anan.platform.modules.international.vo.InternationalVO;

/**
 * 国际化语言控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(value = PathPrefixConstant.INTERNATIONAL, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.INTERNATIONAL, tags = "国际化语言管理")
public class InternationalController implements ICreateController<InternationalCreateDTO, Long>,
        IRetrieveController<InternationalQuery, InternationalVO, InternationalListVO, InternationalPageVO, Long>,
        IUpdateController<InternationalUpdateDTO, Long>,
        IFindOneByFieldController<InternationalVO, Long>,
        IDeleteController<Long> {
    private final InternationalService internationalService;

    public InternationalController(InternationalService internationalService) {
        this.internationalService = internationalService;
    }

    @Override
    public InternationalService getService() {
        return internationalService;
    }
}
