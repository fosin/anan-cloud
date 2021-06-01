package top.fosin.anan.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.model.controller.IStatusController;
import top.fosin.anan.platform.dto.req.AnanInternationalCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanInternationalUpdateDto;
import top.fosin.anan.platform.dto.res.AnanInternationalRespDto;
import top.fosin.anan.platform.service.inter.InternationalService;

/**
 * 国际化语言控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(UrlPrefixConstant.INTERNATIONAL)
@Api(value = UrlPrefixConstant.INTERNATIONAL, tags = "国际化语言管理")
public class InternationalController implements ISimpleController<AnanInternationalRespDto, Long,
        AnanInternationalCreateDto, AnanInternationalRetrieveDto, AnanInternationalUpdateDto>,
        IStatusController<AnanInternationalRespDto, Long, Integer> {

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
    public ResponseEntity<AnanInternationalRespDto> findAllByCode(@PathVariable String code) {
        return ResponseEntity.ok(internationalService.findByCode(code));
    }

    @RequestMapping(path = "/default", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("查找默认语言")
    public ResponseEntity<AnanInternationalRespDto> findByDefaultFlag() {
        return ResponseEntity.ok(internationalService.findByDefaultFlag());
    }

    @Override
    public InternationalService getService() {
        return internationalService;
    }
}
