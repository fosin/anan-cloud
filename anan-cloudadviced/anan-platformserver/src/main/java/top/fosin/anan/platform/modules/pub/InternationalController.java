package top.fosin.anan.platform.modules.pub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.data.constant.PathConstant;
import top.fosin.anan.data.controller.ISimpleController;
import top.fosin.anan.data.result.MultResult;
import top.fosin.anan.data.result.ResultUtils;
import top.fosin.anan.data.result.SingleResult;
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
@RequestMapping(value = PathPrefixConstant.INTERNATIONAL, params = PathPrefixConstant.DEFAULT_VERSION_PARAM)
@Api(value = PathPrefixConstant.INTERNATIONAL, tags = "国际化语言管理")
public class InternationalController implements ISimpleController<InternationalReqDto,InternationalRespDto, Long> {

    private final InternationalService internationalService;

    public InternationalController(InternationalService internationalService) {
        this.internationalService = internationalService;
    }

    @GetMapping(path = "/code/{code}")
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

    @GetMapping(path = "/default")
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
    @ApiImplicitParam(name = "status", value = "需改表的状态值", paramType = "path", required = true, dataTypeClass = Integer.class)
    public MultResult<InternationalRespDto> listByStatus(@NotNull @PathVariable Integer status) {
        return ResultUtils.success(getService().listByStatus(status));
    }

    @Override
    public InternationalService getService() {
        return internationalService;
    }
}
