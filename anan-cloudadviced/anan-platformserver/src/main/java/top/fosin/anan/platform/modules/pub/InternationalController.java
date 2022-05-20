package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.ResultUtils;
import top.fosin.anan.model.result.SingleResult;
import top.fosin.anan.platform.modules.pub.dto.InternationalReqDto;
import top.fosin.anan.platform.modules.pub.dto.InternationalRespDto;
import top.fosin.anan.platform.modules.pub.service.inter.InternationalService;

import javax.validation.constraints.NotNull;

/**
 * 国际化语言控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(UrlPrefixConstant.INTERNATIONAL)
@Api(value = UrlPrefixConstant.INTERNATIONAL, tags = "国际化语言管理")
public class InternationalController implements ISimpleController<InternationalRespDto, Long,
        InternationalReqDto, InternationalReqDto, InternationalReqDto> {

    private final InternationalService internationalService;

    public InternationalController(InternationalService internationalService) {
        this.internationalService = internationalService;
    }

    @RequestMapping(path = "/code/{code}", method = {RequestMethod.GET,
            RequestMethod.POST, RequestMethod.OPTIONS})
    @ApiOperation("根据国际化语言编码查找国际化语言")
    @ApiImplicitParam(
            name = "code",
            value = "国际化语言编码",
            paramType = "path",
            required = true,
            dataTypeClass = String.class
    )
    public SingleResult<InternationalRespDto> findAllByCode(@PathVariable String code) {
        return ResultUtils.success(internationalService.findByCode(code));
    }

    @RequestMapping(path = "/default", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("查找默认语言")
    public SingleResult<InternationalRespDto> findByDefaultFlag() {
        return ResultUtils.success(internationalService.findByDefaultFlag());
    }

    /**
     * 批量改变一个字段的值
     *
     * @param status 更新的字段名
     */
    @GetMapping(value = PathConstant.PATH_STATUS + "/{status}")
    @ApiOperation(value = "改变多条数据的状态")
    @ApiImplicitParam(name = "status", value = "需改表的状态值", paramType = "path",
            required = true, dataTypeClass = Integer.class)
    public MultResult<InternationalRespDto> listByStatus(@NotNull @PathVariable Integer status) {
        return ResultUtils.success(getService().listByStatus(status));
    }

    @Override
    public InternationalService getService() {
        return internationalService;
    }
}
