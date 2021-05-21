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
import top.fosin.anan.platform.dto.res.AnanInternationalRespDto;
import top.fosin.anan.model.controller.ISimpleController;
import top.fosin.anan.platform.dto.req.AnanInternationalCreateDto;
import top.fosin.anan.platform.dto.req.AnanInternationalRetrieveDto;
import top.fosin.anan.platform.dto.req.AnanInternationalUpdateDto;
import top.fosin.anan.platform.service.inter.AnanInternationalService;

import java.util.List;

/**
 * 国际化语言控制层
 *
 * @author fosin
 * @date 2020-12-04 11:05:46
 */
@RestController
@RequestMapping(UrlPrefixConstant.INTERNATIONAL)
@Api(value = UrlPrefixConstant.INTERNATIONAL, tags = "国际化语言管理")
public class AnanInternationalController implements ISimpleController<AnanInternationalRespDto, Long,
        AnanInternationalCreateDto, AnanInternationalRetrieveDto, AnanInternationalUpdateDto> {

    private final AnanInternationalService ananInternationalService;

    public AnanInternationalController(AnanInternationalService ananInternationalService) {
        this.ananInternationalService = ananInternationalService;
    }

    @RequestMapping(path = "/status/{status}", method = {RequestMethod.GET,
            RequestMethod.POST, RequestMethod.OPTIONS})
    @ApiOperation("根据状态码查找所有国际化语言清单")
    @ApiImplicitParam(
            name = "status",
            value = "状态: 0=有效，1=无效",
            paramType = "path",
            required = true,
            dataTypeClass = Integer.class
    )
    public ResponseEntity<List<AnanInternationalRespDto>> findAllByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(ananInternationalService.findAllByStatus(status));
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
        return ResponseEntity.ok(ananInternationalService.findByCode(code));
    }

    @RequestMapping(path = "/default", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("查找默认语言")
    public ResponseEntity<AnanInternationalRespDto> findByDefaultFlag() {
        return ResponseEntity.ok(ananInternationalService.findByDefaultFlag());
    }

    @Override
    public AnanInternationalService getService() {
        return ananInternationalService;
    }
}
