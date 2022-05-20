package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.platform.modules.pub.dto.InternationalCharsetReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalCharsetRespDto;
import top.fosin.anan.platform.modules.pub.service.inter.InternationalCharsetService;

/**
 * 国际化语言字符集控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(UrlPrefixConstant.INTERNATIONAL_CHARSET)
@Api(value = UrlPrefixConstant.INTERNATIONAL_CHARSET, tags = "国际化语言字符集管理")
public class InternationalCharsetController implements ISimpleController<InternationalCharsetRespDto,
        Long,
        InternationalCharsetReqDto,
        InternationalCharsetReqDto,
        InternationalCharsetReqDto> {

    private final InternationalCharsetService internationalCharsetService;

    public InternationalCharsetController(InternationalCharsetService internationalCharsetService) {
        this.internationalCharsetService = internationalCharsetService;
    }

    @RequestMapping(path = "/internationalId/{internationalId}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("根据语言ID查找所有字符集清单")
    @ApiImplicitParam(
            name = "internationalId",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public MultResult<InternationalCharsetRespDto> findAllByInternationalId(@PathVariable Long internationalId) {
        return ResultUtils.success(internationalCharsetService.findAllByInternationalId(internationalId));
    }

    @Override
    public InternationalCharsetService getService() {
        return internationalCharsetService;
    }
}
