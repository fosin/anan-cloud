package top.fosin.anan.platform.modules.organization;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.OrgTreeDto;
import top.fosin.anan.model.controller.BaseController;
import top.fosin.anan.model.controller.IRetrieveTreeController;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.service.inter.OrgEntryService;

/**
 * @author fosin
 */
@RestController
@RequestMapping(UrlPrefixConstant.ORGANIZATION)
@Api(value = UrlPrefixConstant.ORGANIZATION, tags = "机构管理")
@AllArgsConstructor
public class OrgEntryController extends BaseController
        implements IRetrieveTreeController<OrgTreeDto, Long, OrgReqDto> {
    private final OrgEntryService orgEntryService;

    @Override
    public OrgEntryService getService() {
        return orgEntryService;
    }
}
